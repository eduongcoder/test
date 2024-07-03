package com.example.Entity;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleDTO {
	
	private Integer role_id;
	private String role;
	

	
	private List<RolePermissionDTO> rolePermissions;

}
