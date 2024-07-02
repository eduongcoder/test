package com.example.Service;

import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Images;
import com.example.From.ImageForm;
import com.example.Repository.IImageRepository;

@Service
public class ImageService implements IImageService {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IImageRepository service;

	@Autowired
	private IProductService productService;
	@Override
	public List<Images> getAllImage() {
		return service.findAll();

	}

	@Override
	public Images getImageByID(int id) {
		return service.findById(id).get();
	}

	@Override
	public Images createImage(ImageForm form) {
		Images images=modelMapper.map(form, Images.class);
		images.setProductid(productService.getProductByID(form.getProductid()));
		return service.save(images);
	}

}
