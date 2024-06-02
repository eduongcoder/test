package com.example.Form;

import java.util.List;

import com.example.Entity.RelativeDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeForm {
	private int id;
	private String name;
	private int idDepartment;
	private List<RelativeDTO> relatives;
 
}
