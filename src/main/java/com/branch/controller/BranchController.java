package com.branch.controller;

import com.branch.model.Branch;
import com.branch.service.BranchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
@CrossOrigin(origins = "http://localhost:3000")
public class BranchController {

	@Autowired
	private BranchService branchService;

	@PostMapping
	public ResponseEntity<Branch> createBranch(@RequestBody Branch branch) {
		Branch newBranch = branchService.createBranch(branch);
		return ResponseEntity.ok(newBranch);
	}

//	@GetMapping("/{id}")
//	public ResponseEntity<Branch> getBranchById(@PathVariable Long id) {
//		Branch branch = branchService.getBranchById(id);
//		return ResponseEntity.ok(branch);
//	}

	@GetMapping("/{id}")
	public ResponseEntity<Long> getBranchById(@PathVariable Long id) {
		Branch branch = branchService.getBranchById(id);
		Long branchId = branch.getId();
		return ResponseEntity.ok(branchId);
	}

	@GetMapping
	public ResponseEntity<List<Branch>> getAllBranches() {
		List<Branch> branches = branchService.getAllBranches();
		return ResponseEntity.ok(branches);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Branch> updateBranch(@PathVariable Long id, @RequestBody Branch branch) {
		Branch updatedBranch = branchService.updateBranch(id, branch);
		return ResponseEntity.ok(updatedBranch);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
		branchService.deleteBranch(id);
		return ResponseEntity.noContent().build();
	}
	 @GetMapping("/totalbranches")
	    public ResponseEntity<Long> countTotalBranches() {
	        long count = branchService.countTotalBranches();
	        return ResponseEntity.ok(count);
	    }
	
}
