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

@Table(name = "permissions")
@Entity
@Data
@NoArgsConstructor
public class Permissions implements Serializable {
	
	private static final long serialVersionUID = 1;
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int permissions_id;
	
	@Column(name = "permission_name",length = 50,nullable =  false)
	private String permission_name;
	
	@OneToMany(mappedBy = "permission")
	private List<RolePermission> rolePermissions;
}
