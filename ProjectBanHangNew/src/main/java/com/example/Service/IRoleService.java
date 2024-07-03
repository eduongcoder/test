package com.example.Service;

import java.util.List;

import com.example.Entity.Role;
import com.example.From.RoleForm;

public interface IRoleService {
	public List<Role> getAll();
	public Role getRoleById(int id);
	public Role createRole(RoleForm form);
	public Role updateRole(RoleForm form);
}
