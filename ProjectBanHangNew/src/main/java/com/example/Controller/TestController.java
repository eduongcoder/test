package com.example.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.Test;
import com.example.EntityToMap.ProductMessage;
import com.example.Handler.TestWebSocketHandler;
import com.example.Repository.TestRepository;

@RequestMapping("/api/test")
@RestController
public class TestController {

	@Autowired
	private TestRepository testRepository;

	@Autowired
	private TestWebSocketHandler webSocketHandler;

	@GetMapping("/get")
	private List<Test> getTests() {

		return testRepository.findAll();
	}

	@PostMapping("/create")
	private Test createTest(@RequestParam String text) throws IOException{
		Test text0 = new Test();
		text0.setTitle(text);
		Test newText = testRepository.save(text0);
        webSocketHandler.sendTestUpdate("New test added: " + newText.getTitle());
		return newText;
	}
	
	@MessageMapping("/sendProduct")
    @SendTo("/topic/products")
    public ProductMessage sendProduct(ProductMessage message) throws Exception {
        // Xử lý message tại đây
        return message;
    }
}
