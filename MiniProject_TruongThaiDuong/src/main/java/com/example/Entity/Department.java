package com.example.Entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
@Entity
@Table(name="Department")
@Data
@NoArgsConstructor
public class Department implements Serializable{
	private static final long serialVersionUID=1l;

	@Column(name="DepartmentID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="DepartmentName",length = 30,updatable = true)
	private String name;
	
	@OneToMany(mappedBy ="idDepartment")
	private List<Employee> employees;
}
