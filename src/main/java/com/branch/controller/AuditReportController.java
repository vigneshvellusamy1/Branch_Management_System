package com.branch.controller;

import com.branch.model.AuditReport;
import com.branch.service.AuditReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/audit-reports")
@CrossOrigin(origins = "http://localhost:3000")
public class AuditReportController {

    @Autowired
    private AuditReportService auditReportService;

    @PostMapping
    public ResponseEntity<AuditReport> createAuditReport(@RequestBody AuditReport auditReport) {
        AuditReport createdReport = auditReportService.saveAuditReport(auditReport);
        return ResponseEntity.ok(createdReport);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditReport> getAuditReportById(@PathVariable int id) {
        AuditReport auditReport = auditReportService.getAuditReportById(id);
        return ResponseEntity.ok(auditReport);
    }

    @GetMapping
    public ResponseEntity<List<AuditReport>> getAllAuditReports() {
        List<AuditReport> auditReports = auditReportService.getAllAuditReports();
        System.out.println(auditReports);
        return ResponseEntity.ok(auditReports);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuditReport> updateAuditReport(@PathVariable int id, @RequestBody AuditReport auditReport) {
        AuditReport updatedReport = auditReportService.updateAuditReport(id, auditReport);
        return ResponseEntity.ok(updatedReport);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuditReport(@PathVariable int id) {
        auditReportService.deleteAuditReport(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/totalauditcount")
    public ResponseEntity<Long> countTotalBranches() {
        long count = auditReportService.countTotalAudits();
        return ResponseEntity.ok(count);
    }
}
