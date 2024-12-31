package com.branch.service;

import com.branch.model.Branch;
import java.util.List;
import java.util.Optional;


public interface BranchService {
	Branch createBranch(Branch branch);

	Branch getBranchById(Long id);

	List<Branch> getAllBranches();

	Branch updateBranch(Long id, Branch branch);

	void deleteBranch(Long id);

	Optional<Long> getBranchIdById(Long id);

	long countTotalBranches();

	}
