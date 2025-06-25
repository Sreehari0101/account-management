package com.example.account_management.controller;

import com.example.account_management.model.AccountType;
import com.example.account_management.repository.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/account-types")
public class AccountTypeController {

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    // ✅ GET: Retrieve all account types
    @GetMapping
    public List<AccountType> getAllAccountTypes() {
        return accountTypeRepository.findAll();
    }

    // ✅ POST: Create a new account type
    @PostMapping
    public AccountType createAccountType(@RequestBody AccountType accountType) {
        return accountTypeRepository.save(accountType);
    }
}
