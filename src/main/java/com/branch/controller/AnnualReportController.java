package com.branch.controller;

import com.branch.model.AnnualReport;
import com.branch.service.AnnualReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/annual-reports")
@CrossOrigin(origins = "http://localhost:3000")
public class AnnualReportController {

	@Autowired
	private AnnualReportService annualReportService;

	@PostMapping
	public ResponseEntity<AnnualReport> createAnnualReport(@RequestBody AnnualReport annualReport) {
		return ResponseEntity.ok(annualReportService.createAnnualReport(annualReport));
	}
//	@PostMapping
//	public ResponseEntity<AnnualReport> createAnnualReport(@RequestBody AnnualReport report) {
//		AnnualReport savedReport = annualReportService.saveAnnualReport(report);
//		return ResponseEntity.ok(savedReport);
//	}

	@GetMapping("/{id}")
	public ResponseEntity<AnnualReport> getAnnualReportById(@PathVariable Long id) {
		AnnualReport annualReport = annualReportService.getAnnualReportById(id);
		return ResponseEntity.ok(annualReport);
	}

	@GetMapping
	public ResponseEntity<List<AnnualReport>> getAllAnnualReports() {
		List<AnnualReport> annualReports = annualReportService.getAllAnnualReports();
		return ResponseEntity.ok(annualReports);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AnnualReport> updateAnnualReport(@PathVariable Long id,
			@RequestBody AnnualReport annualReport) {
		AnnualReport updatedAnnualReport = annualReportService.updateAnnualReport(id, annualReport);
		return ResponseEntity.ok(updatedAnnualReport);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAnnualReport(@PathVariable Long id) {
		annualReportService.deleteAnnualReport(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/totalannualcount")
	public ResponseEntity<Long> countTotalBranches() {
		long count = annualReportService.countTotalAnnual();
		return ResponseEntity.ok(count);
	}
}
