package com.example.account_management.repository;

import com.example.account_management.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
    Account findByAccountId(String accountId);
}
