package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Lesson8Application {

	public static void main(String[] args) {
		SpringApplication.run(Lesson8Application.class, args);
	}
	
	@GetMapping("/Hello")
	public String hello() {
		return "Hello nha";
	}
}
