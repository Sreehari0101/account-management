package com.example.account_management.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountResponse {
    private String accountId;
    private String customerId;
    private String accountTypeName;
    private String branchName;
    private String status;
    private BigDecimal balance;


    // Getters and Setters
    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getAccountTypeName() { return accountTypeName; }
    public void setAccountTypeName(String accountTypeName) { this.accountTypeName = accountTypeName; }

    public String getBranchName() { return branchName; }
    public void setBranchName(String branchName) { this.branchName = branchName; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

}
