package com.example.Form;


import java.util.List;

import com.example.Entity.EmployeeDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentForm {
	private int id;
	private String name;
	private List<EmployeeDTO> employees;


}
