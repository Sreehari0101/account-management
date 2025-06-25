package com.example.account_management.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

@Document(collection = "ledger_entries")
public class LedgerEntry {

    @Id
    private String id;  // MongoDB _id

    private String accountId;       // Related account's ID
    private String type;            // "DEBIT" or "CREDIT"
    private BigDecimal amount;      // Transaction amount
    private String description;     // Purpose of the transaction
    private String referenceId;     // Optional reference number
    private String status;          // SUCCESS / FAILED etc.
    private Instant timestamp;      // Date and time of transaction

    // ✅ No-arg constructor for MongoDB
    public LedgerEntry() {
        this.timestamp = Instant.now();
    }

    // ✅ Full constructor
    public LedgerEntry(String accountId, String type, BigDecimal amount, String description, String referenceId, String status) {
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.referenceId = referenceId;
        this.status = status;
        this.timestamp = Instant.now();
    }

    // ✅ Getters and Setters

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
