package com.example.account_management.service;

import com.example.account_management.dto.AccountResponse;
import com.example.account_management.dto.TransferRequest;
import com.example.account_management.dto.AccountRequest;
import com.example.account_management.kafka.KafkaProducer;
import com.example.account_management.model.Account;
import com.example.account_management.model.Branch;
import com.example.account_management.model.AccountType;
import com.example.account_management.model.LedgerEntry;
import com.example.account_management.repository.AccountRepository;
import com.example.account_management.repository.BranchRepository;
import com.example.account_management.repository.AccountTypeRepository;
import com.example.account_management.repository.LedgerEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private LedgerEntryRepository ledgerEntryRepository;

    public Account createAccount(AccountRequest request) {
        //Validate Branch
        Query branchQuery = new Query(Criteria.where("branchId").is(request.getBranchId()));
        Branch branch = mongoTemplate.findOne(branchQuery, Branch.class,"branches");
        if(branch == null){
            throw new IllegalArgumentException("Invalid Branch ID" +request.getBranchId());
        }

        //Validate AccountType
        Query accountTypeQuery = new Query(Criteria.where("typeId").is(request.getAccountTypeId()));
        AccountType accountType = mongoTemplate.findOne(accountTypeQuery, AccountType.class,"account_types");
        if(accountType== null){
            throw new IllegalArgumentException("Invalid Account Type ID" +request.getAccountTypeId());
        }

        String generatedAccountId = request.getBranchId() + "-" + request.getCustomerId() + "-" + request.getAccountTypeId();

        Account account = new Account();
        account.setAccountId(generatedAccountId);
        account.setCustomerId(request.getCustomerId());
        account.setBranchId(request.getBranchId());
        account.setAccountTypeId(request.getAccountTypeId());
        account.setStatus("ACTIVE");
        account.setBalance(BigDecimal.ZERO);
        account.setCreatedAt(Instant.now());
        account.setUpdatedAt(Instant.now());

        return mongoTemplate.save(account, "accounts");
    }

    public List<AccountResponse> getAllAccountsWithTypeName() {
        List <Account> accounts = mongoTemplate.findAll(Account.class,"accounts");
        List<AccountResponse> responses = new ArrayList<>();
        for (Account acc : accounts) {
            Query typeQuery = new Query(Criteria.where("typeId").is(acc.getAccountTypeId()));
            AccountType accountType = mongoTemplate.findOne(typeQuery,AccountType.class,"account_types");
            String typeName = (accountType != null) ? accountType.getType() : "UNKNOWN";

            Query branchQuery = new Query(Criteria.where("branchId").is(acc.getBranchId()));
            Branch branch = mongoTemplate.findOne(branchQuery,Branch.class,"branches");
            String branchName = (branch != null) ? branch.getBranchName() : "UNKNOWN";

            AccountResponse accountResponse = new AccountResponse();
            accountResponse.setAccountId(acc.getAccountId());
            accountResponse.setCustomerId(acc.getCustomerId());
            accountResponse.setAccountTypeName(typeName);
            accountResponse.setBranchName(branchName);
            accountResponse.setStatus(acc.getStatus());
            accountResponse.setBalance(acc.getBalance());

            responses.add(accountResponse);
        }
        return responses;
    }

    // ✅ New method to return a single account with type and branch names
    public AccountResponse getAccountDetailsById(String accountId) {
          Query accountQuery = new Query(Criteria.where("_id").is(accountId));
          Account account = mongoTemplate.findOne(accountQuery,Account.class,"accounts");
          if (account == null) return null;

          Query typeQuery = new Query(Criteria.where("typeId").is(account.getAccountTypeId()));
          AccountType accountType = mongoTemplate.findOne(typeQuery,AccountType.class,"account_types");
          String typeName = (accountType != null) ? accountType.getType() : "UNKNOWN";

          Query branchQuery = new Query(Criteria.where("branchId").is(account.getBranchId()));
          Branch branch = mongoTemplate.findOne(branchQuery,Branch.class,"branches");
          String branchName = (branch != null) ? branch.getBranchName() : "UNKNOWN";

          AccountResponse accountResponse = new AccountResponse();
          accountResponse.setAccountId(account.getAccountId());
          accountResponse.setCustomerId(account.getCustomerId());
          accountResponse.setAccountTypeName(typeName);
          accountResponse.setBranchName(branchName);
          accountResponse.setStatus(account.getStatus());
          accountResponse.setBalance(account.getBalance());

          return accountResponse;
    }

    public boolean deleteAccount(String accountId) {
        if (accountRepository.existsById(accountId)) {
            accountRepository.deleteById(accountId);
            return true;
        }
        return false;
    }

    public String transferFunds(TransferRequest request) {
        Account fromAccount = accountRepository.findById(request.getFromAccountId()).orElse(null);
        Account toAccount = accountRepository.findById(request.getToAccountId()).orElse(null);

        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("Invalid source or target account ID.");
        }

        if (fromAccount.getBalance().compareTo(request.getAmount()) < 0) {
            throw new IllegalArgumentException("Insufficient balance in source account.");
        }

        // Debit from source
        fromAccount.setBalance(fromAccount.getBalance().subtract(request.getAmount()));
        fromAccount.setUpdatedAt(Instant.now());

        LedgerEntry debitEntry = new LedgerEntry(
                fromAccount.getAccountId(),
                "DEBIT",
                request.getAmount(),
                "Transfered to account: " + toAccount.getAccountId(),
                "TXN-" + System.currentTimeMillis(),
                "SUCCESS"
        );
        ledgerEntryRepository.save(debitEntry);

        // Credit to target
        toAccount.setBalance(toAccount.getBalance().add(request.getAmount()));
        toAccount.setUpdatedAt(Instant.now());

        LedgerEntry creditEntry = new LedgerEntry(
                toAccount.getAccountId(),
                "CREDIT",
                request.getAmount(),
                "Transfered from account: " + fromAccount.getAccountId(),
                "TXN-" + System.currentTimeMillis(),
                "SUCCESS"
        );
        ledgerEntryRepository.save(creditEntry);

        // Save both accounts
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        // Send Kafka event
        String event = "₹" + request.getAmount() + " transferred from " + fromAccount.getAccountId()
                + " to " + toAccount.getAccountId();
        System.out.println("📤 Sending Kafka Event: " + event);
        kafkaProducer.publishTransferEvent(event);
        System.out.println("✅ Kafka event published: " + event);


        return event;
    }

    public List<String> getAccountIdsByCustomerId(String customerId) {
        List<Account> accounts = accountRepository.findByCustomerId(customerId);

        return accounts.stream()
                .map(Account::getAccountId)
                .toList();
    }


}
