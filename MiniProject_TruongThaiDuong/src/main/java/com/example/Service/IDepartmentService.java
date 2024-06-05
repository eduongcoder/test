package com.example.Service;

import java.util.List;

import com.example.Entity.Department;
import com.example.Form.DepartmentForm;

public interface IDepartmentService {
	public Department getDepartmentByID(int id);
	public void updateDepartmentByID(DepartmentForm form);
	public void deleteDepartmentByID(int id);
	public List<Department> getAllDepartments();
	public Department createDepartment(DepartmentForm form);
	public void deleteDepartmentAll(int id);
}
