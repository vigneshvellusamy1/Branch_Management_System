package com.branch.controller;

import com.branch.model.Strategy;
import com.branch.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/strategies")
public class StrategyController {

	@Autowired
	private StrategyService strategyService;

	@PostMapping
	public ResponseEntity<Strategy> createStrategy(@RequestBody Strategy strategy) {
		return ResponseEntity.ok(strategyService.createStrategy(strategy));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Strategy> getStrategyById(@PathVariable Long id) {
		Strategy strategy = strategyService.getStrategyById(id);
		return ResponseEntity.ok(strategy);
	}

	@GetMapping
	public ResponseEntity<List<Strategy>> getAllStrategies() {
		List<Strategy> strategies = strategyService.getAllStrategies();
		return ResponseEntity.ok(strategies);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Strategy> updateStrategy(@PathVariable Long id, @RequestBody Strategy strategy) {
		Strategy updatedStrategy = strategyService.updateStrategy(id, strategy);
		return ResponseEntity.ok(updatedStrategy);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteStrategy(@PathVariable Long id) {
		strategyService.deleteStrategy(id);
		return ResponseEntity.noContent().build();
	}
}
