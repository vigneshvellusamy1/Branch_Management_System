package com.branch.serviceimp;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.branch.dao.BranchDao;
import com.branch.model.Branch;
import com.branch.service.BranchService;

@Service
public class BranchServiceImpl implements BranchService {

	@Autowired
	private BranchDao branchDao;

	@Autowired
	private KieSession kieSession;

	@Autowired
	private RedisTemplate<String, List<Branch>> redisTemplate;

	private static final String BRANCHES_CACHE_KEY = "branches";

	@Override
//    @CachePut(value = "branches", key = "#result.id")
	public Branch createBranch(Branch branch) {
		// Apply business rules using Drools
		kieSession.insert(branch);
		kieSession.fireAllRules();

		// Save the branch
		Branch savedBranch = branchDao.save(branch);

		// Manually put the saved branch in the Redis cache
		String cacheKey = BRANCHES_CACHE_KEY + ":" + savedBranch.getId();
		redisTemplate.opsForValue().set(cacheKey, getAllBranches()); // This overwrites the cache if it already exists

		return savedBranch; // Return the saved branch
	}

	@Override
	@Cacheable(value = "branches", key = "#id")
	public Branch getBranchById(Long id) {
		return branchDao.findById(id).orElse(null);
	}

	@Override
	public List<Branch> getAllBranches() {
		// Check if branches are in cache
		List<Branch> cachedBranches = redisTemplate.opsForValue().get(BRANCHES_CACHE_KEY);
		if (cachedBranches != null) {
			// Return cached branches if found
			return cachedBranches;
		}

		// If not found in cache, fetch from database
		List<Branch> branchesFromDb = branchDao.findAll();

		// Store the fetched branches in cache with a TTL (e.g., 30 seconds)
		redisTemplate.opsForValue().set(BRANCHES_CACHE_KEY, branchesFromDb, 30, TimeUnit.SECONDS);

		return branchesFromDb;
	}

	@Override
	@CachePut(value = "branches", key = "#id")
	public Branch updateBranch(Long id, Branch branch) {
		Branch existingBranch = branchDao.findById(id).orElse(null);
		if (existingBranch != null) {
			// Update branch details
			existingBranch.setBranchName(branch.getBranchName());
			existingBranch.setLocation(branch.getLocation());
			existingBranch.setBranchManager(branch.getBranchManager());
			existingBranch.setCreatedDate(branch.getCreatedDate());
			existingBranch.setStatus(branch.getStatus());

			// Apply business rules
			kieSession.insert(existingBranch);
			kieSession.fireAllRules();

			// Save and return updated branch
			Branch updatedBranch = branchDao.save(existingBranch);

			// Update the branch in the Redis cache
			String cacheKey = BRANCHES_CACHE_KEY + ":" + updatedBranch.getId();
			redisTemplate.opsForValue().set(cacheKey, getAllBranches()); // This overwrites the cache if it already
																			// exists

			return updatedBranch;
		}
		return null;
	}

	@Override
	@CacheEvict(value = "branches", key = "#id")
	public void deleteBranch(Long id) {
		branchDao.deleteById(id);
		// Remove the branch from the Redis cache
		String cacheKey = BRANCHES_CACHE_KEY + ":" + id;
		redisTemplate.delete(cacheKey);
	}

	@Override
	public Optional<Long> getBranchIdById(Long id) {
		Optional<Branch> branch = branchDao.findById(id);
		return branch.map(Branch::getId);
	}
	  @Override
	    public long countTotalBranches() {
	        return branchDao.count();
	    }

	
}


