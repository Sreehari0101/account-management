package com.example.account_management.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Document(collection = "accounts")
public class Account {

    @Id
    private String id;  // MongoDB's ObjectId

    private String accountId;
    private String customerId;
    private String type;           // e.g., "Savings", "Current"
    private String status;         // e.g., "Active", "Frozen", etc.
    private String linkedBranch;
    private LocalDate closureDate; // nullable
    private BigDecimal currentBalance;

    // Constructors
    public Account() {
    }

    public Account(String accountId, String customerId, String type, String status, String linkedBranch, LocalDate closureDate, BigDecimal currentBalance) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.type = type;
        this.status = status;
        this.linkedBranch = linkedBranch;
        this.closureDate = closureDate;
        this.currentBalance = currentBalance;
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLinkedBranch() {
        return linkedBranch;
    }

    public void setLinkedBranch(String linkedBranch) {
        this.linkedBranch = linkedBranch;
    }

    public LocalDate getClosureDate() {
        return closureDate;
    }

    public void setClosureDate(LocalDate closureDate) {
        this.closureDate = closureDate;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }
}
