package com.branch.serviceimp;

import com.branch.dao.StrategyDao;
import com.branch.model.Strategy;
import com.branch.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyServiceImpl implements StrategyService {

	@Autowired
	private StrategyDao strategyDao;

	@Override
	public Strategy createStrategy(Strategy strategy) {
		return strategyDao.save(strategy);
	}

	@Override
	public Strategy getStrategyById(Long id) {
		return strategyDao.findById(id).orElse(null);
	}

	@Override
	public List<Strategy> getAllStrategies() {
		return strategyDao.findAll();
	}

	@Override
	public Strategy updateStrategy(Long id, Strategy strategy) {
		Strategy existingStrategy = strategyDao.findById(id).orElse(null);
		if (existingStrategy != null) {
			existingStrategy.setStrategyName(strategy.getStrategyName());
			existingStrategy.setProcessSteps(strategy.getProcessSteps());
			existingStrategy.setImplementation(strategy.getImplementation());
			existingStrategy.setCreationDate(strategy.getCreationDate());
			existingStrategy.setStatus(strategy.getStatus());
			return strategyDao.save(existingStrategy);
		}
		return null;
	}

	@Override
	public void deleteStrategy(Long id) {
		strategyDao.deleteById(id);
	}
}
