package com.branch.serviceimp;

import com.branch.dao.AnnualReportDao;
import com.branch.model.AnnualReport;
import com.branch.service.AnnualReportService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AnnualReportServiceImpl implements AnnualReportService {
	private static final Logger logger = LoggerFactory.getLogger(AnnualReportServiceImpl.class);

	private final AnnualReportDao annualReportDao;
	private final KieContainer kieContainer;

	// Constructor injection
	public AnnualReportServiceImpl(AnnualReportDao annualReportDao, KieContainer kieContainer) {
		this.annualReportDao = annualReportDao;
		this.kieContainer = kieContainer;
	}

	/**
	 * Create a new Annual Report with rule processing
	 * 
	 * @param annualReport The annual report to create
	 * @return Saved annual report
	 */
	@Override
	public AnnualReport createAnnualReport(AnnualReport annualReport) {
		// Validate and prepare the annual report
		prepareAnnualReport(annualReport);

		// Create a new Kie session wrapper for processing
		try (KieSessionWrapper sessionWrapper = KieSessionWrapper.create(kieContainer)) {
			// Process the report through rules engine
			processAnnualReportRules(sessionWrapper.getSession(), annualReport);

			// Save and return the report
			return annualReportDao.save(annualReport);
		}
	}
	@Override
	public AnnualReport saveAnnualReport(AnnualReport report) {
		report.calculateRevenue(); // Calculate revenue before saving
		return  annualReportDao.save(report);
	}

	

	/**
	 * Update an existing Annual Report
	 * 
	 * @param id           Identifier of the report to update
	 * @param annualReport New report details
	 * @return Updated annual report
	 */
	@Override
	public AnnualReport updateAnnualReport(Long id, AnnualReport annualReport) {
		// Check if report exists
		AnnualReport existingReport = getAnnualReportById(id);

		// Update existing report with new details
		updateReportDetails(existingReport, annualReport);

		// Validate and prepare the updated report
		prepareAnnualReport(existingReport);

		// Process through rules engine
		try (KieSessionWrapper sessionWrapper = KieSessionWrapper.create(kieContainer)) {
			processAnnualReportRules(sessionWrapper.getSession(), existingReport);
			return annualReportDao.save(existingReport);
		}
	}

	/**
	 * Retrieve an Annual Report by its ID
	 * 
	 * @param id Report identifier
	 * @return Found annual report
	 * @throws EntityNotFoundException if no report is found
	 */
	@Override
	public AnnualReport getAnnualReportById(Long id) {
		return annualReportDao.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Annual Report not found with id: " + id));
	}

	/**
	 * Retrieve all Annual Reports
	 * 
	 * @return List of all annual reports
	 */
	@Override
	public List<AnnualReport> getAllAnnualReports() {
		return annualReportDao.findAll();
	}

	/**
	 * Delete an Annual Report by its ID
	 * 
	 * @param id Report identifier to delete
	 * @throws EntityNotFoundException if no report is found
	 */
	@Override
	public void deleteAnnualReport(Long id) {
		// Verify report exists before deleting
		if (!annualReportDao.existsById(id)) {
			throw new EntityNotFoundException("Annual Report not found with id: " + id);
		}
		annualReportDao.deleteById(id);
	}

	/**
	 * Optional methods - uncomment and implement if needed
	 */

	/**
	 * Update details of an existing report
	 * 
	 * @param existingReport Report to be updated
	 * @param newReport      New report details
	 */
	private void updateReportDetails(AnnualReport existingReport, AnnualReport newReport) {
		// Selectively update fields, preserving existing values if new ones are null
		if (newReport.getBranch() != null)
			existingReport.setBranch(newReport.getBranch());
		if (newReport.getYear() != null)
			existingReport.setYear(newReport.getYear());
		if (newReport.getLoanType() != null)
			existingReport.setLoanType(newReport.getLoanType().toLowerCase());
		if (newReport.getLoanAmountAllocated() != null)
			existingReport.setLoanAmountAllocated(newReport.getLoanAmountAllocated());
		if (newReport.getLoanAmountUsed() != null)
			existingReport.setLoanAmountUsed(newReport.getLoanAmountUsed());
		// Add other fields as necessary
	}

	/**
	 * Prepare annual report by setting default values and normalizing data
	 * 
	 * @param annualReport Report to prepare
	 */
	private void prepareAnnualReport(AnnualReport annualReport) {
		// Validate input
		validateAnnualReport(annualReport);

		// Set default values if not provided
		annualReport.setStatus(Optional.ofNullable(annualReport.getStatus()).orElse("Active"));
		annualReport.setProcessSteps(Optional.ofNullable(annualReport.getProcessSteps()).orElse("INITIATED"));
		annualReport.setValidationStatus(Optional.ofNullable(annualReport.getValidationStatus()).orElse("PENDING"));
		annualReport.setReportDate(Optional.ofNullable(annualReport.getReportDate()).orElse(LocalDate.now()));

		// Ensure loan amount fields are not null
		annualReport.setLoanAmountAllocated(Optional.ofNullable(annualReport.getLoanAmountAllocated())
				.filter(s -> !s.trim().isEmpty()).orElse("0.00"));
		annualReport.setLoanAmountUsed(
				Optional.ofNullable(annualReport.getLoanAmountUsed()).filter(s -> !s.trim().isEmpty()).orElse("0.00"));

		// Normalize loan type
		if (annualReport.getLoanType() != null) {
			annualReport.setLoanType(annualReport.getLoanType().toLowerCase());
		}
	}

	/**
	 * Process annual report through Drools rules engine
	 * 
	 * @param kieSession Active KIE session
	 * @param report     Annual report to process
	 */
	private void processAnnualReportRules(KieSession kieSession, AnnualReport report) {
		try {
			// Insert the report into the rules session
			kieSession.insert(report);

			// Fire all rules
			int fired = kieSession.fireAllRules();

			logger.info("Number of rules fired: {}", fired);
		} catch (Exception e) {
			// Log the error and rethrow
			logger.error("Error processing annual report rules", e);
			throw new IllegalStateException("Error processing annual report rules", e);
		}
	}

	/**
	 * Validate annual report before processing
	 * 
	 * @param report Report to validate
	 * @throws IllegalArgumentException for validation failures
	 */
	private void validateAnnualReport(AnnualReport report) {
		// Comprehensive validation
		if (report == null) {
			throw new IllegalArgumentException("Annual Report cannot be null");
		}

		// Validate required fields
		if (report.getBranch() == null) {
			throw new IllegalArgumentException("Branch is required");
		}
		if (report.getYear() == null) {
			throw new IllegalArgumentException("Year is required");
		}

		// Validate loan type
		if (report.getLoanType() == null || report.getLoanType().trim().isEmpty()) {
			throw new IllegalArgumentException("Loan type is required");
		}

		// Validate loan type is either home or agriculture
		String loanType = report.getLoanType().toLowerCase();
		if (!loanType.equals("home") && !loanType.equals("agriculture")) {
			throw new IllegalArgumentException("Invalid loan type. Allowed types are: home, agriculture");
		}

		// Validate loan amounts
		validateLoanAmounts(report);
	}

	/**
	 * Validate loan amounts for an annual report
	 * 
	 * @param report Report to validate loan amounts
	 * @throws IllegalArgumentException for invalid loan amounts
	 */
	private void validateLoanAmounts(AnnualReport report) {
		// Validate loan amounts
		String allocatedAmount = Optional.ofNullable(report.getLoanAmountAllocated()).map(String::trim)
				.filter(s -> !s.isEmpty()).orElse("0.00");

		String usedAmount = Optional.ofNullable(report.getLoanAmountUsed()).map(String::trim).filter(s -> !s.isEmpty())
				.orElse("0.00");

		try {
			double allocated = Double.parseDouble(allocatedAmount);
			double used = Double.parseDouble(usedAmount);

			// Additional validations
			if (used > allocated) {
				throw new IllegalArgumentException("Used amount cannot be greater than allocated amount");
			}
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid number format in loan amounts", e);
		}
	}

	public long countTotalAnnual() {
		return annualReportDao.count();
	}

}