package com.example.Service;

import java.util.List;

import com.example.Entity.Images;
import com.example.From.ImageForm;

public interface IImageService {

	public List<Images> getAllImage();
	public Images getImageByID(int id);
	public Images createImage(ImageForm form);
}
