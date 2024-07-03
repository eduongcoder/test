package com.example.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Permissions;
import com.example.From.PermissionsForm;
import com.example.Repository.IPermissionsRepository;

@Service
public class PermissionsService implements IPermissionsService{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IPermissionsRepository service;

	@Override
	public List<Permissions> getAll() {
		// TODO Auto-generated method stub
		return service.findAll();
	}

	@Override
	public Permissions createPermissions(PermissionsForm form) {
		Permissions permissions=modelMapper.map(form, Permissions.class);
		return service.save(permissions);
	}

	@Override
	public Permissions getPermissionsById(int id) {
		// TODO Auto-generated method stub
		return service.findById(id).get();
	}
}
