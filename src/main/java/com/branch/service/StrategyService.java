package com.branch.service;

import com.branch.model.Strategy;
import java.util.List;

public interface StrategyService {
	Strategy createStrategy(Strategy strategy);

	Strategy getStrategyById(Long id);

	List<Strategy> getAllStrategies();

	Strategy updateStrategy(Long id, Strategy strategy);

	void deleteStrategy(Long id);
}
