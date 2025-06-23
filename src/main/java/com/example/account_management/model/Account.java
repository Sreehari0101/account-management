package com.example.account_management.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "accounts")  // Maps to MongoDB collection
public class Account {
    @Id
    private String id;
    private String name;
    private String type;
    private Double balance;

    public Account() {}

    public Account(String name, String type, Double balance) {
        this.name = name;
        this.type = type;
        this.balance = balance;
    }

    // Getters and Setters (or use Lombok)
    public String getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public Double getBalance() { return balance; }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setType(String type) { this.type = type; }
    public void setBalance(Double balance) { this.balance = balance; }
}

