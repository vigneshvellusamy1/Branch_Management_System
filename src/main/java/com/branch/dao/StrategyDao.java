package com.branch.dao;

import com.branch.model.Strategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StrategyDao extends JpaRepository<Strategy, Long> {
}

