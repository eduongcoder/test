package com.example.Service;

import java.util.Base64;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.example.Entity.Images;

@Component
public class ImageHandelService {
	@Autowired
	private ImageService service;	
	@Autowired
	private ISaleService saleService;
	
	
	@Async("threadPoolTaskExecutor")
	public CompletableFuture<String> getImageBase64(int imageID) {
		
		Images images=service.getImageByID(imageID);
		
		
		if (images != null) {
            byte[] imageData = images.getImageByte();

			// Mã hóa mảng byte thành chuỗi Base64
			String base64Image = Base64.getEncoder().encodeToString(images.getImageByte());
			// Tạo URL dữ liệu từ chuỗi Base64
			String imageUrl = "data:image/jpeg;base64," + base64Image;
			return CompletableFuture.completedFuture(imageUrl);
		} else {
			return CompletableFuture.completedFuture("Image not found");
		}
	}
	
	public String getImageBase64String(int imageID) {
		
		Images images=service.getImageByID(imageID);
		
		
		if (images != null) {
            byte[] imageData = images.getImageByte();

			// Mã hóa mảng byte thành chuỗi Base64
			String base64Image = Base64.getEncoder().encodeToString(images.getImageByte());
			// Tạo URL dữ liệu từ chuỗi Base64
			String imageUrl = "data:image/jpeg;base64," + base64Image;
			return imageUrl;
		} else {
			return "Image not found";
		}
	}
}
