package com.example;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.Test;
import com.example.Entity.TestDTO;
import com.example.Repository.ITestRepository;

@RequestMapping("/api/test")
@RestController
public class TestController {
	@Autowired 
	private ITestRepository service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<TestDTO> getAll() {
		List<Test> list=service.findAll();
		List<TestDTO> dtos=modelMapper.map(list, new TypeToken<List<TestDTO>>()
		{}.getType());
		return dtos;
	}
}
