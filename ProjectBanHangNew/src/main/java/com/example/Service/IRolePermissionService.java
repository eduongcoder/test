package com.example.Service;

import com.example.Entity.RolePermission;
import com.example.From.RolePermissionForm;

public interface IRolePermissionService {
	public RolePermission createRolePermission(RolePermissionForm form);
	public boolean deletePermission(int idRole, int idPermission);
}
