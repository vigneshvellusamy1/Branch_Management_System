package com.branch.service;

import com.branch.model.AuditReport;
import java.util.List;

public interface AuditReportService {
	AuditReport saveAuditReport(AuditReport auditReport);

	AuditReport getAuditReportById(int id);

	List<AuditReport> getAllAuditReports();

	AuditReport updateAuditReport(int id, AuditReport auditReport);

	void deleteAuditReport(int id);
	
	long countTotalAudits();
}
