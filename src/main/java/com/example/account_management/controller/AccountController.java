package com.example.account_management.controller;

import com.example.account_management.dto.AccountRequest;
import com.example.account_management.model.Account;
import com.example.account_management.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;


    @PostMapping
    public Account createAccount(@RequestBody AccountRequest request) {
        Account account = new Account();
        account.setAccountId(request.getAccountId());
        account.setCustomerId(request.getCustomerId());
        account.setAccountType(request.getAccountType());
        account.setStatus(request.getStatus());
        account.setBalance(request.getBalance());
        account.setUpdatedAt(LocalDateTime.now());

        return accountRepository.save(account);
    }

<<<<<<< Updated upstream


    // ✅ GET API to retrieve all account data
    @GetMapping
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }



=======
    @DeleteMapping("/{accountId}")
    public String deleteAccount(@PathVariable String accountId) {
        if (accountRepository.existsById(accountId)) {
            accountRepository.deleteById(accountId);
            return "Account with ID " + accountId + " deleted successfully.";
        } else {
            return "Account with ID " + accountId + " not found.";
        }
    }

>>>>>>> Stashed changes
}
