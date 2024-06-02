package com.example.Entity;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
@Data
@NoArgsConstructor
public class EmployeeDTO {
	@NonNull
	private Integer id;
//	@NonNull
	private String name;
//	@NonNull
	private Integer idDepartment;

//	@NonNull
	private List<RelativeDTO> Relatives;
}
