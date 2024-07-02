package com.example.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.TypeOfProductGender;
import com.example.Repository.ITypeOfProductGenderRepository;

@Service
public class TypeOfProductGenderService implements ITypeOfProductGenderService{
	@Autowired
	private ITypeOfProductGenderRepository service;

	@Override
	public List<TypeOfProductGender> getAllList() {
		// TODO Auto-generated method stub
		return service.findAll();
	}

	@Override
	public TypeOfProductGender getSizeByID(int id) {
		// TODO Auto-generated method stub
		return service.findById(id).get();
	}
}
