package com.example.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Relative;
import com.example.Form.RelativeFrom;
import com.example.Repository.IRelativeRepository;

@Service
public class RelativeService implements IRelativeService {

	@Autowired
	private IRelativeRepository service;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Relative getRelativeByID(int id) {
		return service.findById(id).get();
	}

	@Override
	public void updateRelativeByID(RelativeFrom form) {
		Relative relative = modelMapper.map(form, Relative.class);
		service.save(relative);
	}

	@Override
	public void deleteRelativeByID(int id) {
		service.deleteById(id);
	}

	@Override
	public List<Relative> getAllRelatives() {
		return service.findAll();
	}

	@Override
	public Relative createRelative(RelativeFrom form) {
		Relative relative = modelMapper.map(form, Relative.class);
		service.save(relative);
		return relative;
	}

}
