package com.example.account_management.controller;

import com.example.account_management.model.Branch;
import com.example.account_management.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @Autowired
    private BranchRepository branchRepository;

    // ✅ POST: Add a new branch
    @PostMapping
    public Branch createBranch(@RequestBody Branch branch) {
        return branchRepository.save(branch);
    }

    // ✅ GET: Fetch all branches
    @GetMapping
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }
}
