package com.example.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Department;
import com.example.Entity.EmployeeDTO;
import com.example.Form.DepartmentForm;
import com.example.Form.EmployeeForm;
import com.example.Repository.IDepartmentRepository;

@Service
public class DepartmentService implements IDepartmentService{
	
	@Autowired
	private IDepartmentRepository service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Department getDepartmentByID(int id) {
		return service.findById(id).get();
	}

	@Override
	public void updateDepartmentByID(DepartmentForm form) {
		Department department=modelMapper.map(form, Department.class);
		service.save(department);
	}

	@Override
	public void deleteDepartmentByID(int id) {
		service.deleteById(id);
	}

	@Override
	public List<Department> getAllDepartments() {
		return service.findAll();
	}

	@Override
	public Department createDepartment(DepartmentForm form) {
		Department department=modelMapper.map(form, Department.class);
		service.save(department);
		return department;
	}

}
