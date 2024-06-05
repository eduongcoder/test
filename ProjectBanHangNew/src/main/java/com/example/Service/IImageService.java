package com.example.Service;

import java.util.List;

import com.example.Entity.Images;

public interface IImageService {

	public List<Images> getAllImage();
	public Images getImageByID(int id);

}
