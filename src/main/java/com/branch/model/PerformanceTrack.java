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
@Table(name = "performance_tracks")
public class PerformanceTrack {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Branch branch;
	private String results;
	private LocalDate trackingDate;
	private String validationStatus;
	private String status;

	@PrePersist
	protected void onCreate() {
		this.trackingDate = LocalDate.now();
	}
}