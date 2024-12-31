package com.branch.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "annual_reports")
public class AnnualReport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Branch branch;
	private Integer year;
	private String processSteps;
	private String validationStatus;
	private LocalDate reportDate;
	private String loanAmountAllocated;
	private String loanAmountUsed;
	private String remainingLoanBalance;
	private String loanType;
	private int interestRate;
	private String status;
	private BigDecimal revenue;
	

	@PrePersist
	protected void onCreate() {
		this.reportDate = LocalDate.now();
	}

	
	
	// Method tocalculate revenue
	@PostPersist
	public void calculateRevenue() {
		System.err.println("Calculate Revenue Calling ...");
		BigDecimal loanUsed = new BigDecimal(loanAmountUsed);
		BigDecimal interest = loanUsed.multiply(new BigDecimal(interestRate)).divide(new BigDecimal(100));
		this.revenue = interest;
	}
}