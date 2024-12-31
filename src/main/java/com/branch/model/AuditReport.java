package com.branch.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuditReport implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Branch branch;

	private String auditDescription; // Changed to String for a descriptive text
	private String auditCreationDate;

	private String auditFrom;

	private String auditTo;

	private String auditDepartment;

	@Column(nullable = false) // Ensure that auditStatus cannot be null
	private String auditStatus = "completed";

	@PrePersist
	protected void onCreate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		this.auditCreationDate = dateFormat.format(new Date()); // Format the current date
	}

}
