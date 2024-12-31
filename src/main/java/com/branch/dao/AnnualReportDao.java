package com.branch.dao;

import com.branch.model.AnnualReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnualReportDao extends JpaRepository<AnnualReport, Long> {
	long count();
}
