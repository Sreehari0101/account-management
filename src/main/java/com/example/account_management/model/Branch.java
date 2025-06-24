package com.example.account_management.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "branches")
public class Branch {

    @Id
    private String id;  // This is MongoDB’s auto-generated ObjectId

    private String branchId;     // Your manual 4-digit ID (e.g., "0001")
    private String branchName;

    // Constructors
    public Branch() {}

    public Branch(String branchId, String branchName) {
        this.branchId = branchId;
        this.branchName = branchName;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
