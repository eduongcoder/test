package com.example.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Role;
import com.example.From.RoleForm;
import com.example.Repository.IRoleRepository;

@Service
public class RoleService implements IRoleService{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IRoleRepository service;

	@Override
	public List<Role> getAll() {
		// TODO Auto-generated method stub
		return service.findAll();
	}

	@Override
	public Role getRoleById(int id) {
		// TODO Auto-generated method stub
		return service.findById(id).get();
	}

	@Override
	public Role createRole(RoleForm form) {
		Role role=modelMapper.map(form, Role.class);
		return service.save(role);
	}

	@Override
	public Role updateRole(RoleForm form) {
		Role role=getRoleById(form.getRole_id());
		role.setRole(form.getRole());
		return service.save(role);
	}
	
	
}
