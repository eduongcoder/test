package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.Entity.Department;

@Repository
public interface IDepartmentRepository extends JpaRepository<Department,Integer>{

	
}
