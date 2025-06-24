package com.example.account_management.repository;

import com.example.account_management.model.AccountType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeRepository extends MongoRepository<AccountType, String> {
    // Optional custom finder
    AccountType findByTypeId(String typeId);
}
