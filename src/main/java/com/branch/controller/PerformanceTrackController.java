//package com.branch.controller;
//
//import com.branch.model.PerformanceTrack;
//import com.branch.service.PerformanceTrackService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/performance-tracks")
//public class PerformanceTrackController {
//
//	@Autowired
//	private PerformanceTrackService performanceTrackService;
//
//	@PostMapping
//	public ResponseEntity<PerformanceTrack> createPerformanceTrack(@RequestBody PerformanceTrack performanceTrack) {
//		return ResponseEntity.ok(performanceTrackService.createPerformanceTrack(performanceTrack));
//	}
//
//	@GetMapping("/{id}")
//	public ResponseEntity<PerformanceTrack> getPerformanceTrackById(@PathVariable Long id) {
//		PerformanceTrack performanceTrack = performanceTrackService.getPerformanceTrackById(id);
//		return ResponseEntity.ok(performanceTrack);
//	}
//
//	@GetMapping
//	public ResponseEntity<List<PerformanceTrack>> getAllPerformanceTracks() {
//		List<PerformanceTrack> performanceTracks = performanceTrackService.getAllPerformanceTracks();
//		return ResponseEntity.ok(performanceTracks);
//	}
//
//	@PutMapping("/{id}")
//	public ResponseEntity<PerformanceTrack> updatePerformanceTrack(@PathVariable Long id,
//			@RequestBody PerformanceTrack performanceTrack) {
//		PerformanceTrack updatedPerformanceTrack = performanceTrackService.updatePerformanceTrack(id, performanceTrack);
//		return ResponseEntity.ok(updatedPerformanceTrack);
//	}
//
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Void> deletePerformanceTrack(@PathVariable Long id) {
//		performanceTrackService.deletePerformanceTrack(id);
//		return ResponseEntity.noContent().build();
//	}
//}
