package com.example.account_management.controller;

import com.example.account_management.model.AccountType;
import com.example.account_management.repository.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account-types")
public class AccountTypeController {

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    // ✅ POST: Add a new account type
    @PostMapping
    public AccountType createAccountType(@RequestBody AccountType accountType) {
        return accountTypeRepository.save(accountType);
    }

    // ✅ GET: Fetch all account types
    @GetMapping
    public List<AccountType> getAllAccountTypes() {
        return accountTypeRepository.findAll();
    }

    // ✅ GET by typeId (optional)
    @GetMapping("/{typeId}")
    public AccountType getByTypeId(@PathVariable String typeId) {
        return accountTypeRepository.findByTypeId(typeId);
    }
}
