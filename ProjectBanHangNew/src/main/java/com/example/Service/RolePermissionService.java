package com.example.Service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.RolePermission;
import com.example.Entity.RolePermissionId;
import com.example.From.RolePermissionForm;
import com.example.Repository.IRolePermissionRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class RolePermissionService  implements IRolePermissionService{
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IRolePermissionRepository rolePermissionService;
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IPermissionsService permissionsService;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public RolePermission createRolePermission(RolePermissionForm form) {
		RolePermission rolePermission= new RolePermission();
		rolePermission.setPermission(permissionsService.getPermissionsById(form.getPermission()));
		rolePermission.setRole(roleService.getRoleById(form.getRole()));
		return rolePermissionService.save(rolePermission);

		
	}

	@Override
	public boolean deletePermission(int idRole, int idPermission) {
		RolePermissionId rolePermissionId=new RolePermissionId();
		rolePermissionId.setPermission(idPermission);
		rolePermissionId.setRole(idRole);
		try {
			 rolePermissionService.deleteById(rolePermissionId);
			 return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
}
