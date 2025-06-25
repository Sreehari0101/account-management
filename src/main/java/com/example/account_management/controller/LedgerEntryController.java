package com.example.account_management.controller;

import com.example.account_management.model.LedgerEntry;
import com.example.account_management.repository.LedgerEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/ledger")
public class LedgerEntryController {

    @Autowired
    private LedgerEntryRepository ledgerEntryRepository;

    // Create a new ledger entry
    @PostMapping
    public LedgerEntry createLedgerEntry(@RequestBody LedgerEntry ledgerEntry) {
        return ledgerEntryRepository.save(ledgerEntry);
    }

    // Get all ledger entries
    @GetMapping
    public List<LedgerEntry> getAllEntries() {
        return ledgerEntryRepository.findAll();
    }

    // Get a single ledger entry by ID
    @GetMapping("/{id}")
    public Optional<LedgerEntry> getLedgerEntryById(@PathVariable String id) {
        return ledgerEntryRepository.findById(id);
    }

    // Get all entries for a specific account
    @GetMapping("/account/{accountId}")
    public List<LedgerEntry> getEntriesByAccountId(@PathVariable String accountId) {
        return ledgerEntryRepository.findByAccountId(accountId);
    }

    // Delete a ledger entry by ID
    @DeleteMapping("/{id}")
    public void deleteLedgerEntry(@PathVariable String id) {
        ledgerEntryRepository.deleteById(id);
    }
}
