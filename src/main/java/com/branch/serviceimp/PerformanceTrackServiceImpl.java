//package com.branch.serviceimp;
//
//import com.branch.dao.PerformanceTrackDao;
//import com.branch.model.PerformanceTrack;
//import com.branch.service.PerformanceTrackService;
//import org.kie.api.runtime.KieSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class PerformanceTrackServiceImpl implements PerformanceTrackService {
//
//	@Autowired
//	private KieSession kieSession;
//
//	@Autowired
//	private PerformanceTrackDao performanceTrackDao;
//
//	@Override
//	public PerformanceTrack createPerformanceTrack(PerformanceTrack performanceTrack) {
//		kieSession.insert(performanceTrack);
//		kieSession.fireAllRules();
//		return performanceTrackDao.save(performanceTrack);
//	}
//
//	@Override
//	public PerformanceTrack getPerformanceTrackById(Long id) {
//		return performanceTrackDao.findById(id).orElse(null);
//	}
//
//	@Override
//	public List<PerformanceTrack> getAllPerformanceTracks() {
//		return performanceTrackDao.findAll();
//	}
//
//	@Override
//	public PerformanceTrack updatePerformanceTrack(Long id, PerformanceTrack performanceTrack) {
//		PerformanceTrack existingPerformanceTrack = performanceTrackDao.findById(id).orElse(null);
//		if (existingPerformanceTrack != null) {
//			existingPerformanceTrack.setResults(performanceTrack.getResults());
//			existingPerformanceTrack.setTrackingDate(performanceTrack.getTrackingDate());
//			existingPerformanceTrack.setValidationStatus(performanceTrack.getValidationStatus());
//			existingPerformanceTrack.setStatus(performanceTrack.getStatus());
//			kieSession.insert(existingPerformanceTrack);
//			kieSession.fireAllRules();
//			return performanceTrackDao.save(existingPerformanceTrack);
//		}
//		return null;
//	}
//
//	@Override
//	public void deletePerformanceTrack(Long id) {
//		performanceTrackDao.deleteById(id);
//	}
//}
