package com.example.account_management.controller;

import com.example.account_management.model.Account;
import com.example.account_management.repository.AccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // ✅ GET all accounts
    @GetMapping
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // ✅ POST new account → this will create DB/collection on first insert
    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountRepository.save(account);
    }
}