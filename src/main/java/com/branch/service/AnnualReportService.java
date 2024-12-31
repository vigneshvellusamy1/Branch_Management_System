package com.branch.service;

import com.branch.model.AnnualReport;
import java.util.List;

public interface AnnualReportService {
	AnnualReport createAnnualReport(AnnualReport annualReport);

	AnnualReport saveAnnualReport(AnnualReport report);

	AnnualReport getAnnualReportById(Long id);

	List<AnnualReport> getAllAnnualReports();

	AnnualReport updateAnnualReport(Long id, AnnualReport annualReport);

	void deleteAnnualReport(Long id);

	long countTotalAnnual();

}
