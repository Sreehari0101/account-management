package com.example.account_management.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "ledger_entries")
public class LedgerEntry {

    @Id
    private String id;  // MongoDB _id

    private String accountId;       // Related account's ID
    private String type;            // "DEBIT" or "CREDIT"
    private BigDecimal amount;      // Transaction amount
    private String description;     // Purpose of the transaction
    private String referenceId;     // Optional reference number
    private LocalDateTime timestamp; // Date and time of transaction

    // Constructors
    public LedgerEntry() {
        this.timestamp = LocalDateTime.now();
    }

    public LedgerEntry(String accountId, String type, BigDecimal amount, String description, String referenceId) {
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.referenceId = referenceId;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
