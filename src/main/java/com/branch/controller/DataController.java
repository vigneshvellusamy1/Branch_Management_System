//package com.branch.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.branch.dao.MyJsonRepository;
//import com.branch.model.MyJson;
//
//@RestController
//@RequestMapping("/api/data")
//public class DataController {
//
//	@Autowired
//	private MyJsonRepository dataRepository;
//
//	@PostMapping
//	public MyJson createData(@RequestBody MyJson data) {
//		return dataRepository.save(data);
//	}
//
//	@GetMapping
//	public List<MyJson> getAllData() {
//		return dataRepository.findAll();
//	}
//}
