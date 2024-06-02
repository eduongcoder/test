package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Department;

public interface IDepartmentRepository  extends JpaRepository<Department, Integer>{

}
