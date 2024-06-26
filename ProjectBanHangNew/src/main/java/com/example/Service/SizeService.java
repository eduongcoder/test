package com.example.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Size;
import com.example.From.SizeForm;
import com.example.Repository.ISizeRepository;

@Service
public class SizeService implements ISizeService {

	@Autowired
	private ISizeRepository service;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<Size> getAllList() {
		// TODO Auto-generated method stub
		return service.findAll();
	}

	@Override
	public Size getSizeByID(int id) {
		// TODO Auto-generated method stub
		return service.findById(id).get();
	}

	@Override
	public boolean deleteSize(int id) {
		try {
			service.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public Size createSize(SizeForm form) {
		Size size = modelMapper.map(form, Size.class);
		
		return service.save(size);
	}

}
