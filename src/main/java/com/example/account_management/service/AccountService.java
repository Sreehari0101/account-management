package com.example.account_management.service;

import com.example.account_management.dto.AccountRequest;
import com.example.account_management.model.Account;
import com.example.account_management.model.Branch;
import com.example.account_management.model.AccountType;
import com.example.account_management.repository.AccountRepository;
import com.example.account_management.repository.BranchRepository;
import com.example.account_management.repository.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

        // 🔍 Check if Branch ID exists
        Branch branch = branchRepository.findByBranchId(request.getBranchId());
        if (branch == null) {
            throw new IllegalArgumentException("Invalid branch ID: " + request.getBranchId());
        }

        // 🔍 Check if Account Type ID exists
        AccountType accountType = accountTypeRepository.findByTypeId(request.getAccountTypeId());
        if (accountType == null) {
            throw new IllegalArgumentException("Invalid account type ID: " + request.getAccountTypeId());
        }

        // ✅ Proceed to create account
        Account account = new Account();
        account.setAccountId(request.getAccountId());
        account.setCustomerId(request.getCustomerId());
        account.setBranchId(request.getBranchId());
        account.setAccountTypeId(request.getAccountTypeId());
        account.setStatus(request.getStatus());
        account.setBalance(request.getBalance());
        account.setUpdatedAt(LocalDateTime.now());

        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(String accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }

    public boolean deleteAccount(String accountId) {
        if (accountRepository.existsById(accountId)) {
            accountRepository.deleteById(accountId);
            return true;
        }
        return false;
    }
}
