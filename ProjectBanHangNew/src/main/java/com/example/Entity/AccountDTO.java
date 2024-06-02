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

	private Integer accounts_id;
	
	private String username;

	
	private String password;

	private String email;

	private Short height;

	private Short weight;
	
	private String phoneNumber;
	
	private Date dayOfBirth;

	private Date created_at;
	
	private Date updated_at;
	
	private Gender gender;
	
	private List<AddressesDTO> addresses;

	private List<OrdersDTO> orders;

}
