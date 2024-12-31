package com.branch.model;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "employee_performances")
public class EmployeePerformance implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "performance_id")
    private Long id;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "sales_figures")
    private double salesFigures;

    @Column(name = "customer_satisfaction_score")
    private int customerSatisfactionScore;

    @Column(name = "task_completion_rate")
    private int taskCompletionRate;

    @Column(name = "branch_id")
    private Long branchId;

	public void setBranchId(Branch branch) {
		// TODO Auto-generated method stub
		
	}
}

