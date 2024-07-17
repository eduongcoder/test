package com.example.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

import com.example.Handler.TestWebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	private final TestWebSocketHandler testWebSocketHandler;

	public WebSocketConfig(TestWebSocketHandler testWebSocketHandler) {
		this.testWebSocketHandler = testWebSocketHandler;
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(testWebSocketHandler, "/ws/test").setAllowedOrigins("*");
		registry.addHandler(testWebSocketHandler, "/ws/product").setAllowedOrigins("*");

	}


	
	@Bean
	public ServletServerContainerFactoryBean createWebSocketContainer() {
		ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
		container.setMaxTextMessageBufferSize(8192);
		container.setMaxBinaryMessageBufferSize(8192);
		return container;
	}

}
//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
//	  @Override
//	    public void configureMessageBroker(MessageBrokerRegistry config) {
//	        config.enableSimpleBroker("/topic"); // Configure message broker
//	        config.setApplicationDestinationPrefixes("/app");
//	    }
//
//	    @Override
//	    public void registerStompEndpoints(StompEndpointRegistry registry) {
//	        registry.addEndpoint("/ws/product").setAllowedOrigins("*").withSockJS();
//	    }
//}
