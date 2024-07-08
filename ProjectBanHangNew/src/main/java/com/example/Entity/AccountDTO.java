package com.example.Entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import com.example.Enum.Gender;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDTO {

	private Integer account_id;

	private String username;

	private String password;

	private String email;

	private Short height;

	private Short weight;
	
	private boolean islogin;

	private String avatarString;

	private String phoneNumber;

	private Date dayOfBirth;

	private LocalDateTime created_at;

	private LocalDateTime updated_at;

	private Gender gender;

	private List<AddressesDTO> addresses;

	private List<OrdersDTO> orders;
	private Integer roleID;
	private String role;

	private List<HistoryLoginDTO> historyLogins;

	private List<RolePermissionDTO> rolePermission;
}
