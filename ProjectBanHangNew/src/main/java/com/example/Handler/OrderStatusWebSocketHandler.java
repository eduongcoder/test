package com.example.Handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component
public class OrderStatusWebSocketHandler extends TextWebSocketHandler{
	private final List<WebSocketSession> sessions = new ArrayList<>();

    private final RestTemplate restTemplate;

	
    public OrderStatusWebSocketHandler(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
//        System.out.println("New WebSocket connection established: " + session.getId());
	}
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
	    String payload = message.getPayload();
	    String idAccount = session.getUri().getQuery().split("=")[1];
	
        System.out.println("Received payload: " + payload);

        try {
            JsonObject jsonObject = JsonParser.parseString(payload).getAsJsonObject();
//            System.out.println("Parsed JSON: " + jsonObject.toString());

            String id = jsonObject.get("id").getAsString();
//            System.out.println("Type: " + type);
            String idOrder = jsonObject.get("idOrder").getAsString();

            String status = jsonObject.get("status").getAsString();

            System.out.println(idOrder+"hahahaha"+status);
          
//                String url = jsonObject.get("url").getAsString();
//                System.out.println("URL: " + url);
//                String response = restTemplate.getForObject(url, String.class);

                // Gửi lại phản hồi tới tất cả các client kết nối
                synchronized (sessions) {
                    for (WebSocketSession webSocketSession : sessions) {
                        if (webSocketSession != null && webSocketSession.isOpen()) {
                        	if (idAccount.equals(id)) {
                                webSocketSession.sendMessage(new TextMessage(idOrder+" "+status));                              
							}
                            
                        } else {
                            System.out.println("Null or closed WebSocketSession detected");
                        }
                    }
                }
        
               
            
        } catch (com.google.gson.JsonSyntaxException e) {
            System.out.println("Error parsing JSON: Invalid JSON syntax");
            session.sendMessage(new TextMessage("Error parsing JSON: Invalid JSON syntax"));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            session.sendMessage(new TextMessage("Error: " + e.getMessage()));
        }
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessions.remove(session);
//        System.out.println("WebSocket connection closed: " + session.getId());

	}

	public void sendTestUpdate(String test) throws IOException {
		for (WebSocketSession session : sessions) {
			if (session.isOpen()) {
				session.sendMessage(new TextMessage(test));
			}
		}
	}
}
