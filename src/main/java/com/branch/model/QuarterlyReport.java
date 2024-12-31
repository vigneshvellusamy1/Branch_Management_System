package com.branch.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "quarterly_reports")
public class QuarterlyReport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Branch branch;
	private Integer quarterNumber;
	private Integer year;
	private String rules;
	private String ruleFlow;
	private LocalDate reportDate;
	private String status;

	@PrePersist
	protected void onCreate() {
		this.reportDate = LocalDate.now();
	}
}
