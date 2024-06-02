package com.example.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Images;
import com.example.Repository.IImageProductRepository;

@Service
public  class ImageService implements IImageService{
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IImageProductRepository service;
	

	@Override
	public List<Images> getAllImage() {
		return service.findAll();
 
	}

   
}
