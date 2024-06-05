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
	private IImageService service1;

	@Autowired
	private IVariantService serviceVariant;

	@Async("threadPoolTaskExecutor")
	public CompletableFuture<String> getImageBase64(int imageId) {
		Images imageEntityOptional = serviceVariant.getVariantByID(imageId).getImages().get(0);
		
//		service1.getImageByID(imageId);
		if (imageEntityOptional != null) {
			byte[] imageData = imageEntityOptional.getImage_url();
			// Mã hóa mảng byte thành chuỗi Base64
			String base64Image = Base64.getEncoder().encodeToString(imageData);
			// Tạo URL dữ liệu từ chuỗi Base64
			String imageUrl = "data:image/jpeg;base64," + base64Image;
			return CompletableFuture.completedFuture(imageUrl);
		} else {
			return CompletableFuture.completedFuture("Image not found");
		}
	}
}
