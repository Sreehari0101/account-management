package com.example.account_management.repository;

import com.example.account_management.model.AuditEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AuditEntryRepository extends MongoRepository<AuditEntry, String> {
    List<AuditEntry> findByAccountId(String accountId);
}
