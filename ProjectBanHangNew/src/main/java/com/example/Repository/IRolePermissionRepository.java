package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.RolePermission;
import com.example.Entity.RolePermissionId;

public interface IRolePermissionRepository extends JpaRepository<RolePermission, RolePermissionId>{

}
