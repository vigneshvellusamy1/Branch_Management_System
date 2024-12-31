package com.branch.dao;

import com.branch.model.PerformanceTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceTrackDao extends JpaRepository<PerformanceTrack, Long> {
}
