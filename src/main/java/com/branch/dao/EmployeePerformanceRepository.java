package com.branch.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.branch.model.EmployeePerformance;

@Repository
public interface EmployeePerformanceRepository extends JpaRepository<EmployeePerformance, Long> {
	// Additional query methods can be defined here
}
