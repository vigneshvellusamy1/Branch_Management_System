package com.branch.dao;

import com.branch.model.AuditReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditReportRepository extends JpaRepository<AuditReport, Integer> {
	long count();

}
