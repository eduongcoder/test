package com.example.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Employee;
import com.example.Form.EmployeeForm;
import com.example.Repository.IEmployeeRepository;

@Service
public class EmployeeService implements IEmployeeService {
	@Autowired
	private IEmployeeRepository service;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Employee getEmployeeByID(int id) {
		return service.findById(id).get();
	}

	@Override
	public Employee createEmployee(EmployeeForm form) {
		Employee employee = modelMapper.map(form, Employee.class);
		service.save(employee);
		return employee;
	}

	@Override
	public void updateEmployeeByID(EmployeeForm form) {
		Employee employee = modelMapper.map(form, Employee.class);
		service.save(employee);
	}

	@Override
	public void deleteEmployeeByID(int id) {
		service.deleteById(id);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return service.findAll();
	}

}
