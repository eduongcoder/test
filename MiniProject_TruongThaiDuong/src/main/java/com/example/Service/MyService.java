package com.example.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.Entity.TestEntity;
import com.example.Repository.TestRepository;

import java.util.Base64;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
public class MyService {

	@Autowired
	private Executor threadPoolTaskExecutor;

	@Autowired
	private TestRepository repositoryservice;

//    @Async("threadPoolTaskExecutor")
//    public CompletableFuture<String> processString(int id) {
//        // Xử lý chuỗi dài ở đây
//    	TestEntity entity=repositoryservice.findById(id).get();
//    	if (entity !=null) {
//    		 // String result = entity.getChuoi(); // Ví dụ đơn giản: chuyển thành chữ hoa
//    	        return CompletableFuture.completedFuture(entity.getChuoi());
//		}
//      return null;
//    }
	@Async("threadPoolTaskExecutor")
	public CompletableFuture<String> getImageBase64(int imageId) {
		TestEntity imageEntityOptional = repositoryservice.findById(imageId).get();

		if (imageEntityOptional != null) {
			byte[] imageData = imageEntityOptional.getChuoi();
			// Mã hóa mảng byte thành chuỗi Base64
			String base64Image = Base64.getEncoder().encodeToString(imageData);
			int length = base64Image.length();
			// Trả về chuỗi Base64 cùng với tổng số ký tự
			String result = "Base64: " + length;
			return CompletableFuture.completedFuture(result);
		} else {
			return CompletableFuture.completedFuture(null);
		}
	}
}