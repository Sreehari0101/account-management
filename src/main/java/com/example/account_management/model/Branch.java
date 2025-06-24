package com.example.account_management.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "branches")
public class Branch {

    @Id
    private String branchId;   // e.g., BR001

    private String branchName; // e.g., "Kottayam Main"

    // Constructors
    public Branch() {}

    public Branch(String branchId, String branchName) {
        this.branchId = branchId;
        this.branchName = branchName;
    }

    // Getters and Setters
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
