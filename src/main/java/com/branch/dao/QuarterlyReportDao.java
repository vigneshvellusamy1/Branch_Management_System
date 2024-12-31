package com.branch.dao;

import com.branch.model.QuarterlyReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuarterlyReportDao extends JpaRepository<QuarterlyReport, Long> {
}
