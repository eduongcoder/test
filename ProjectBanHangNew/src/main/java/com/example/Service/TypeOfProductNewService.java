package com.example.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.TypeOfProductNew;
import com.example.Repository.ITypeOfProductNewRepository;

@Service
public class TypeOfProductNewService implements ITypeOfProductNewService{

	@Autowired
	private ITypeOfProductNewRepository service;
	
	@Override
	public List<TypeOfProductNew> getAllList() {
		// TODO Auto-generated method stub
		return service.findAll();
	}

	@Override
	public TypeOfProductNew getSizeByID(int id) {
		// TODO Auto-generated method stub
		return service.findById(id).get();
	}
	
}
