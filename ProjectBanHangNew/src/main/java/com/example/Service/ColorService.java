package com.example.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Color;
import com.example.From.ColorForm;
import com.example.Repository.IColorRepository;

@Service
public class ColorService implements IColorService{
	
	@Autowired
	private IColorRepository service;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<Color> getAllList() {
		// TODO Auto-generated method stub
		return service.findAll();
	}

	@Override
	public Color getColorByID(int id) {
		// TODO Auto-generated method stub
		return service.findById(id).get();
	}

	@Override
	public boolean deleteColor(int id) {
		try {
			service.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Color createColor(ColorForm form) {
		Color color= modelMapper.map(form, Color.class);
		
		return service.save(color);
	}
	
	
}
