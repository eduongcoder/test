package com.example.Entity;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientProductDTO {
	private Integer variantid;
	
	private Integer accountID;

	private Date dayorder;
	private String clientname;
	private String clientphonenumber;
	private String clientemail;	
	private String clientcity;	
	private String clientward;	
	private String clientdistrict;
	private String clientstreet;	
	private Integer productbuy;
	
}
