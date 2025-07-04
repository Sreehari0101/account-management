package com.example.account_management.controller;

import com.example.account_management.dto.AccountRequest;
import com.example.account_management.dto.AccountResponse;
import com.example.account_management.dto.TransferRequest;
import com.example.account_management.model.Account;
import com.example.account_management.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody AccountRequest request) {
        try {
            Account created = accountService.createAccount(request);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    }


    @GetMapping
    public ResponseEntity<?> getAllAccounts() {
        List<AccountResponse> accounts = accountService.getAllAccountsWithTypeName();

        if(accounts.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No accounts found.");
        }

        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable String accountId) {
        AccountResponse account = accountService.getAccountDetailsById(accountId);

        if (account == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Account with ID " + accountId + " not found.");
        }
        return ResponseEntity.ok(account);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable String accountId) {
        boolean deleted = accountService.deleteAccount(accountId);
        if (deleted) {
            return ResponseEntity.ok("Account with ID " + accountId + " deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Account with ID " + accountId + " not found.");
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transferFunds(@RequestBody TransferRequest request) {
        System.out.println(request);
        try {
            String result = accountService.transferFunds(request);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Transfer failed due to an unexpected error.");
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getAccountIdsByCustomerId(@PathVariable String customerId) {
        List<String> accountIds = accountService.getAccountIdsByCustomerId(customerId);

        if (accountIds.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No accounts found for customer ID: " + customerId);
        }
        return ResponseEntity.ok(accountIds);
    }



}
