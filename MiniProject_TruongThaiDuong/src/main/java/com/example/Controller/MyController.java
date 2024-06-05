package com.example.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.TestEntity;
import com.example.Repository.TestRepository;
import com.example.Service.MyService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
	

@RequestMapping("api/myservice")
@RestController
public class MyController {

    @Autowired
    private MyService myService;

    @Autowired
    private TestRepository service;
    
    @GetMapping("/process")
    public CompletableFuture<List<String>> process() {
    	List<CompletableFuture<String>> aList=new ArrayList<>();
    	List<TestEntity>  list=service.findAll();
    	for (TestEntity testEntity : list) {
			
    		aList.add(myService.getImageBase64(testEntity.getIdtest())) ;
		}
    	   return CompletableFuture.allOf(aList.toArray(new CompletableFuture[0]))
    	            .thenApply(v -> aList.stream()
    	                .map(CompletableFuture::join)
    	                .collect(Collectors.toList()));
    }
}
