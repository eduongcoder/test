package com.example.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.Entity.Images;
import com.example.Repository.IImageRepository;

@Service
public class ImageService implements IImageService {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IImageRepository service;

	@Override
	public List<Images> getAllImage() {
		return service.findAll();

	}



	@Override
	public Images getImageByID(int id) {
		return service.findById(id).get();
	}





}
