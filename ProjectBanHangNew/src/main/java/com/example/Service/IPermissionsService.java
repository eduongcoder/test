package com.example.Service;

import java.util.List;

import com.example.Entity.Permissions;
import com.example.From.PermissionsForm;

public interface IPermissionsService {
	public List<Permissions> getAll();
	public Permissions getPermissionsById(int id);
	public Permissions createPermissions(PermissionsForm form);
}
