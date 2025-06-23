package com.example.account_management.repository;

import com.example.account_management.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
    // No implementation needed! Spring will auto-generate everything
}