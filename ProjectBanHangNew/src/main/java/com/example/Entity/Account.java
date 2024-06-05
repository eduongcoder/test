package com.example.Entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.example.Entity.Orders;
import com.example.Enum.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
public class Account implements Serializable {
	private static final long serialVersionUID = 1;
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int account_id;
	@Column(name = "username",length = 255,updatable = true)
	private String username;

	@Column(name = "password",length = 255,updatable = true)
	private String password;

	@Column(name = "email", length = 255)
	private String email;

	@Column(name = "height", updatable = true)
	private short height;

	@Column(name = "weight", updatable = true)
	private short weight;
	
	@Column(name = "phoneNumber", length = 10)
	private String phoneNumber;
	
	@Column(name = "dayOfBirth", updatable = true)
	private Date dayOfBirth;

	@Column(name = "created_at", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime created_at;
	
	@Column(name = "updated_at",  updatable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private LocalDateTime updated_at;
	
	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@OneToMany(mappedBy = "account_id")
	private List<Addresses> addresses;
	
	@OneToMany(mappedBy = "account")
	private List<Orders> orders;
	


}
