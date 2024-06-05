package com.example.Service;

import java.util.List;

import com.example.Entity.Employee;
import com.example.Form.EmployeeForm;

public interface IEmployeeService {
	public Employee getEmployeeByID(int id);
	public void updateEmployeeByID(EmployeeForm form);
	public void deleteEmployeeByID(int id);
	public List<Employee> getAllEmployees();
	public Employee createEmployee(EmployeeForm form);
	public void deleteEmployeeAll(int id);
}
