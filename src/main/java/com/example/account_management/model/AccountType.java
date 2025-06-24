package com.example.account_management.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "account_types")
public class AccountType {

    @Id
    private String Id;  // This is the default MongoDB _id (ObjectId)

    private String typeId;   // Your manual 3-digit ID, e.g., "001"
    private String type;     // SAVINGS, CURRENT, LOAN
    private String description;

    // Constructors
    public AccountType() {}

    public AccountType(String typeId, String type, String description) {
        this.typeId = typeId;
        this.type = type;
        this.description = description;
    }

    // Getters and Setters
    public String getId() {
        return Id;
    }

    public void setId(String mongoId) {
        this.Id = mongoId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
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
