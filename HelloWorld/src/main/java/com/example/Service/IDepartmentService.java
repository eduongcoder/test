package com.example.Service;

import java.util.List;

import com.example.Entity.Department;


public interface IDepartmentService {


	public Department getDepartmentByID(int id);

	public void createDepartment(Department department);

	public void updateDepartment(Department department);

	public void deleteDepartment(int id);

	public boolean isDepartmentExistsByID(int id);
	public List<Department> getAllDepartments();
}
