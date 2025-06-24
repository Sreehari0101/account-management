package com.example.account_management.repository;

import com.example.account_management.model.Branch;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends MongoRepository<Branch, String> {
    // Optional custom query
    Branch findByBranchId(String branchId);
}
