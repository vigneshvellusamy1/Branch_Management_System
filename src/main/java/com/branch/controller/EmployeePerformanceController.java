package com.branch.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.branch.model.EmployeePerformance;
import com.branch.service.EmployeePerformanceService;
import com.branch.service.BranchService;
import com.branch.model.Branch;

@RestController
@RequestMapping("/api/performances")
public class EmployeePerformanceController {

    @Autowired
    private EmployeePerformanceService performanceService;

    @Autowired
    private BranchService branchService;

    @GetMapping
    public List<EmployeePerformance> getAllPerformances() {
        return performanceService.getAllPerformances();
    }

    @GetMapping("/{id}")
    public EmployeePerformance getPerformanceById(@PathVariable Long id) {
        return performanceService.getPerformanceById(id);
    }

    @PostMapping
    public EmployeePerformance createPerformance(@RequestBody EmployeePerformance performance) {
        Branch branch = branchService.getBranchById(performance.getBranchId());
        performance.setBranchId(branch); // Set the branch reference
        EmployeePerformance savedPerformance = performanceService.savePerformance(performance);
        branch.setEmployeeCount(branch.getEmployeeCount() + 1); // Increase employee count
        branchService.createBranch(branch);
        return savedPerformance;
    }



    @PutMapping("/{id}")
    public EmployeePerformance updatePerformance(@PathVariable Long id, @RequestBody EmployeePerformance performance, @RequestParam Branch branchId) {
        performance.setId(id);
        performance.setBranchId(branchId);
        return performanceService.savePerformance(performance);
    }

    @DeleteMapping("/{id}")
    public void deletePerformance(@PathVariable Long id) {
        EmployeePerformance performance = performanceService.getPerformanceById(id);
        if (performance != null) {
            Branch branch = branchService.getBranchById(performance.getBranchId());
            performanceService.deletePerformance(id);
            branch.setEmployeeCount(branch.getEmployeeCount() - 1); // Decrease employee count
            branchService.createBranch(branch);
        }
    }
}
