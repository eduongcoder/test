package com.example.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Variant;
import com.example.From.VariantForm;
import com.example.Repository.IVariantRepository;

@Service
public class VariantService  implements IVariantService{

	@Autowired
	private IVariantRepository service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<Variant> getAllVariants() {
		// TODO Auto-generated method stub
		return service.findAll();
	}

	@Override
	public Variant getVariantByID(int id) {
		// TODO Auto-generated method stub
		return service.findById(id).get();
	}

	@Override
	public int deleteVariant(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Variant updateVariant(VariantForm form) {
		// TODO Auto-generated method stub
		return null;
	}

}
