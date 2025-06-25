package com.example.account_management.service;

import com.example.account_management.dto.AccountResponse;
import com.example.account_management.dto.AccountRequest;
import com.example.account_management.model.Account;
import com.example.account_management.model.Branch;
import com.example.account_management.model.AccountType;
import com.example.account_management.repository.AccountRepository;
import com.example.account_management.repository.BranchRepository;
import com.example.account_management.repository.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    public Account createAccount(AccountRequest request) {
        Branch branch = branchRepository.findByBranchId(request.getBranchId());
        if (branch == null) {
            throw new IllegalArgumentException("Invalid branch ID: " + request.getBranchId());
        }

        AccountType accountType = accountTypeRepository.findByTypeId(request.getAccountTypeId());
        if (accountType == null) {
            throw new IllegalArgumentException("Invalid account type ID: " + request.getAccountTypeId());
        }

        Account account = new Account();
        account.setAccountId(request.getAccountId());
        account.setCustomerId(request.getCustomerId());
        account.setBranchId(request.getBranchId());
        account.setAccountTypeId(request.getAccountTypeId());
        account.setStatus(request.getStatus());
        account.setBalance(request.getBalance());
        account.setUpdatedAt(Instant.now());

        return accountRepository.save(account);
    }

    public List<AccountResponse> getAllAccountsWithTypeName() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountResponse> responses = new ArrayList<>();

        for (Account acc : accounts) {
            AccountType type = accountTypeRepository.findByTypeId(acc.getAccountTypeId());
            String typeName = (type != null) ? type.getType() : "UNKNOWN";

            Branch branch = branchRepository.findByBranchId(acc.getBranchId());
            String branchName = (branch != null) ? branch.getBranchName() : "UNKNOWN";

            AccountResponse dto = new AccountResponse();
            dto.setAccountId(acc.getAccountId());
            dto.setCustomerId(acc.getCustomerId());
            dto.setAccountTypeId(acc.getAccountTypeId());
            dto.setAccountTypeName(typeName);
            dto.setBranchId(acc.getBranchId());
            dto.setBranchName(branchName);
            dto.setStatus(acc.getStatus());
            dto.setBalance(acc.getBalance());

            responses.add(dto);
        }

        return responses;
    }

    // ✅ New method to return a single account with type and branch names
    public AccountResponse getAccountDetailsById(String accountId) {
        Account acc = accountRepository.findById(accountId).orElse(null);
        if (acc == null) return null;

        AccountType type = accountTypeRepository.findByTypeId(acc.getAccountTypeId());
        String typeName = (type != null) ? type.getType() : "UNKNOWN";

        Branch branch = branchRepository.findByBranchId(acc.getBranchId());
        String branchName = (branch != null) ? branch.getBranchName() : "UNKNOWN";

        AccountResponse dto = new AccountResponse();
        dto.setAccountId(acc.getAccountId());
        dto.setCustomerId(acc.getCustomerId());
        dto.setAccountTypeId(acc.getAccountTypeId());
        dto.setAccountTypeName(typeName);
        dto.setBranchId(acc.getBranchId());
        dto.setBranchName(branchName);
        dto.setStatus(acc.getStatus());
        dto.setBalance(acc.getBalance());

        return dto;
    }

    public boolean deleteAccount(String accountId) {
        if (accountRepository.existsById(accountId)) {
            accountRepository.deleteById(accountId);
            return true;
        }
        return false;
    }
}
