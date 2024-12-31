package com.branch.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@Table(name = "branches")
public class Branch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "branch_id")
	private Long id;

	@Column(name = "branch_name")
	private String branchName;

	@Column(name = "branch_location")
	private String location;

	@Column(name = "branch_manager")
	private String branchManager;

	@Column(name = "created_date")
	private String createdDate;

	@Column(name = "branch_status")
	private String status;
	
	@Column(name = "employee_count") 
	private int employeeCount;

	@PrePersist
	protected void onCreate() {
		// Format LocalDate to String in yyyy-MM-dd format
		this.createdDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
}