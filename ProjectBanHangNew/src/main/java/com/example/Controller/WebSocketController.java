package com.example.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.EntityToMap.ProductMessage;

@Controller
public class WebSocketController {

	@MessageMapping("/sendProduct")
	@SendTo("/topic/products")
	public ProductMessage sendProduct(ProductMessage message) throws Exception {
		// Xử lý message tại đây
		return message;
	}
}
