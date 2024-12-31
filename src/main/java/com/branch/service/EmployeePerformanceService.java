package com.branch.service;

import java.util.List;
import com.branch.model.EmployeePerformance;

public interface EmployeePerformanceService {
	List<EmployeePerformance> getAllPerformances();

	EmployeePerformance getPerformanceById(Long id);

	EmployeePerformance savePerformance(EmployeePerformance performance);

	void deletePerformance(Long id);
}