package com.example.Entity;


import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Data
@NoArgsConstructor
public class DepartmentDTO {
	@NonNull
	private Integer id;
	@NonNull
	private String name;
//	@NonNull
	private List<EmployeeDTO> employees;

}
