//package com.branch.controller;
//
//import com.branch.model.QuarterlyReport;
//import com.branch.service.QuarterlyReportService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/quarterly-reports")
//public class QuarterlyReportController {
//
//	@Autowired
//	private QuarterlyReportService quarterlyReportService;
//
//	@PostMapping
//	public ResponseEntity<QuarterlyReport> createQuarterlyReport(@RequestBody QuarterlyReport quarterlyReport) {
//		return ResponseEntity.ok(quarterlyReportService.createQuarterlyReport(quarterlyReport));
//	}
//
//	@GetMapping("/{id}")
//	public ResponseEntity<QuarterlyReport> getQuarterlyReportById(@PathVariable Long id) {
//		QuarterlyReport quarterlyReport = quarterlyReportService.getQuarterlyReportById(id);
//		return ResponseEntity.ok(quarterlyReport);
//	}
//
//	@GetMapping
//	public ResponseEntity<List<QuarterlyReport>> getAllQuarterlyReports() {
//		List<QuarterlyReport> quarterlyReports = quarterlyReportService.getAllQuarterlyReports();
//		return ResponseEntity.ok(quarterlyReports);
//	}
//
//	@PutMapping("/{id}")
//	public ResponseEntity<QuarterlyReport> updateQuarterlyReport(@PathVariable Long id,
//			@RequestBody QuarterlyReport quarterlyReport) {
//		QuarterlyReport updatedQuarterlyReport = quarterlyReportService.updateQuarterlyReport(id, quarterlyReport);
//		return ResponseEntity.ok(updatedQuarterlyReport);
//	}
//
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Void> deleteQuarterlyReport(@PathVariable Long id) {
//		quarterlyReportService.deleteQuarterlyReport(id);
//		return ResponseEntity.noContent().build();
//	}
//}
