package com.example.Entity;

import java.sql.Date;

import com.example.Enum.Gender;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class AccountDTO {
//	@NonNull
	private Integer accountid;
	private Gender accountgender;
	private Short accountheight;
	private Short accountweight;
//	@NonNull
	private String accountemail;
//	@NonNull
	private String accountphonenumber;
	private Date accountdayofbrith;
//	@NonNull
	private String accountusername;
	
//	@NonNull
	private String accountpassword;

}
