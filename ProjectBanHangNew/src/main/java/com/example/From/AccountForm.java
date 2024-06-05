package com.example.From;



import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import com.example.Entity.AddressesDTO;
import com.example.Entity.OrdersDTO;
import com.example.Enum.Gender;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountForm {
	private Integer account_id;
	
	private String username;

	
	private String password;

	private String email;

	private Short height;

	private Short weight;
	
	private String phoneNumber;
	
	private Date dayOfBirth;

	private LocalDateTime created_at;
	
	private LocalDateTime updated_at;
	
	private Gender gender;
	
	private List<AddressesDTO> addresses;

	private List<OrdersDTO> orders;
	

}
