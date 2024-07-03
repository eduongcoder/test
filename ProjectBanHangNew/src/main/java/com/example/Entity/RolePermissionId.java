package com.example.Entity;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RolePermissionId implements Serializable {

	private static final long serialVersionUID = 1;

	private int role;

	private int permission;
}
