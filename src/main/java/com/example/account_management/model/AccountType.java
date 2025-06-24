package com.example.account_management.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "account_types")
public class AccountType {

    @Id
    private String id;

    private String type;
    private String description;

    // Constructors
    public AccountType() {}

    public AccountType(String id, String type, String description) {
        this.id = id;
        this.type = type;
        this.description = description;
    }

    // Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
