package com.branch.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.branch.dao.AuditReportRepository;
import com.branch.model.AuditReport;
import com.branch.service.AuditReportService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuditReportServiceImpl implements AuditReportService {
	@Autowired
	private AuditReportRepository auditReportRepository;

	@Cacheable(value = "auditReports", key = "#id")
	@Override
	public AuditReport getAuditReportById(int id) {
		return auditReportRepository.findById(id).orElse(null);
	}

//	@Cacheable(value = "allAuditReports")
	@Override
	public List<AuditReport> getAllAuditReports() {
		List<AuditReport> r = auditReportRepository.findAll();
		System.out.println(r);
		return r;
	}

	@CachePut(value = "auditReports", key = "#result.id")
	@Override
	public AuditReport saveAuditReport(AuditReport auditReport) {
		return auditReportRepository.save(auditReport);
	}

	@CachePut(value = "auditReports", key = "#id")
	@Override
	public AuditReport updateAuditReport(int id, AuditReport auditReport) {
		AuditReport existingReport = auditReportRepository.findById(id).orElse(null);
		if (existingReport != null) {
			existingReport.setBranch(auditReport.getBranch());
			existingReport.setAuditDescription(auditReport.getAuditDescription());
			existingReport.setAuditFrom(auditReport.getAuditFrom());
			existingReport.setAuditTo(auditReport.getAuditTo());
			existingReport.setAuditDepartment(auditReport.getAuditDepartment());
			existingReport.setAuditStatus(auditReport.getAuditStatus());
			return auditReportRepository.save(existingReport);
		}
		return null;
	}

	@CacheEvict(value = "auditReports", key = "#id")
	@Override
	public void deleteAuditReport(int id) {
		auditReportRepository.deleteById(id);
	}

	@Override
	public long countTotalAudits() {
		return auditReportRepository.count();
	}

}