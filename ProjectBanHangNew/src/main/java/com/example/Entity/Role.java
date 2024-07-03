package com.example.Entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "role")
@Entity
@Data
@NoArgsConstructor
public class Role implements Serializable {
	private static final long serialVersionUID = 1;

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int role_id;
	@Column(name = "role", length = 100, updatable = true)
	private String role;

	@OneToMany(mappedBy = "roleID")
	private List<Account> accounts;
	
	@OneToMany(mappedBy = "role")
	private List<RolePermission> rolePermissions;
}
