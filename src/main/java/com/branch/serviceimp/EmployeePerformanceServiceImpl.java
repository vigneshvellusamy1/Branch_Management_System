package com.branch.serviceimp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.branch.dao.EmployeePerformanceRepository;
import com.branch.model.EmployeePerformance;
import com.branch.service.EmployeePerformanceService;

@Service
public class EmployeePerformanceServiceImpl implements EmployeePerformanceService {

	@Autowired
	private EmployeePerformanceRepository performanceRepository;

	@Override
	public List<EmployeePerformance> getAllPerformances() {
		return performanceRepository.findAll();
	}

	@Override
	public EmployeePerformance getPerformanceById(Long id) {
		return performanceRepository.findById(id).orElse(null);
	}

	@Override
	public EmployeePerformance savePerformance(EmployeePerformance performance) {
		return performanceRepository.save(performance);
	}

	@Override
	public void deletePerformance(Long id) {
		performanceRepository.deleteById(id);
	}
}
