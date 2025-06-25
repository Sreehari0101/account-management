package com.example.account_management.dto;

import java.math.BigDecimal;

public class AccountRequest {
    private String accountId;
    private String customerId;
    private String accountTypeId; // NEW
    private String branchId;      // NEW
    private String status;
    private BigDecimal balance;

    // Getters and setters
    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getAccountTypeId() { return accountTypeId; }
    public void setAccountTypeId(String accountTypeId) { this.accountTypeId = accountTypeId; }

    public String getBranchId() { return branchId; }
    public void setBranchId(String branchId) { this.branchId = branchId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
}
