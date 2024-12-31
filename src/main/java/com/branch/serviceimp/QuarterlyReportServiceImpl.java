//package com.branch.serviceimp;
//
//import com.branch.dao.QuarterlyReportDao;
//import com.branch.model.QuarterlyReport;
//import com.branch.service.QuarterlyReportService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class QuarterlyReportServiceImpl implements QuarterlyReportService {
//
//	@Autowired
//	private QuarterlyReportDao quarterlyReportDao;
//	
//	   @Autowired
//	    private RedisTemplate<String, List<QuarterlyReport>> redisTemplate;
//	   
//
//	    private static final String QUARTERLYREPORTS_CACHE_KEY = "quarterly_reports";
//
//	
//
//	@Override
//	public QuarterlyReport createQuarterlyReport(QuarterlyReport quarterlyReport) {
//		return quarterlyReportDao.save(quarterlyReport);
//	}
//
//	
//	
//	
//	@Override
//    @Cacheable(value = "quarterly_reports", key = "#id")
//	public QuarterlyReport getQuarterlyReportById(Long id) {
//		System.out.println("from DB");
//		return quarterlyReportDao.findById(id).orElse(null);
//	}
//
//	@Override
//	public List<QuarterlyReport> getAllQuarterlyReports() {
//		return quarterlyReportDao.findAll();
//	}
//
//	@Override
//	public QuarterlyReport updateQuarterlyReport(Long id, QuarterlyReport quarterlyReport) {
//		QuarterlyReport existingQuarterlyReport = quarterlyReportDao.findById(id).orElse(null);
//		if (existingQuarterlyReport != null) {
//			existingQuarterlyReport.setQuarterNumber(quarterlyReport.getQuarterNumber());
//			existingQuarterlyReport.setYear(quarterlyReport.getYear());
//			existingQuarterlyReport.setRules(quarterlyReport.getRules());
//			existingQuarterlyReport.setRuleFlow(quarterlyReport.getRuleFlow());
//			existingQuarterlyReport.setReportDate(quarterlyReport.getReportDate());
//			existingQuarterlyReport.setStatus(quarterlyReport.getStatus());
//			return quarterlyReportDao.save(existingQuarterlyReport);
//		}
//		return null;
//	}
//
//	@Override
//	public void deleteQuarterlyReport(Long id) {
//		quarterlyReportDao.deleteById(id);
//	}
//}
