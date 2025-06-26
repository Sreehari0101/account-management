package com.example.account_management.repository;

import com.example.account_management.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {

    // ✅ Custom Query Method: Fetch all accounts by customerId
    List<Account> findByCustomerId(String customerId);

}

