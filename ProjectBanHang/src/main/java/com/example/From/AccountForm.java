package com.example.From;

import java.sql.Date;

import com.example.Enum.Gender;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountForm {
	private Integer accountid;
	
	private String accountemail;
	private String accountphonenumber;
	private String accountusername;
	private String accountpassword;
	private Integer clientid;
	

}
