package com.example.account_management.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "audit_entries")
public class AuditEntry {

    @Id
    private String id;  // MongoDB _id

    private String accountId;       // Related account's ID
    private String changedField;    // e.g., "status", "linkedBranch"
    private String oldValue;        // Before the update
    private String newValue;        // After the update
    private LocalDateTime timestamp; // When the change was made

    // Constructors
    public AuditEntry() {
        this.timestamp = LocalDateTime.now();
    }

    public AuditEntry(String accountId, String changedField, String oldValue, String newValue) {
        this.accountId = accountId;
        this.changedField = changedField;
        this.oldValue = oldValue;
        this.newValue = newValue;
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

    public String getChangedField() {
        return changedField;
    }

    public void setChangedField(String changedField) {
        this.changedField = changedField;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
