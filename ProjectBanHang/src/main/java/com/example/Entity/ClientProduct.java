package com.example.Entity;


import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@IdClass(ClientProductId.class)
public class ClientProduct {
	@Id
	@ManyToOne
	@JoinColumn(name = "variantid")
	private Variant variantID;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "accountid")
	private Account accountID;

	

	@Column(name = "clientname",length = 255,updatable = true)
	private String clientname;
	@Column(name = "timeorder")
	private Date dayorder;
	@Column(name = "clientphonenumber",length = 10,updatable = true)
	private String clientphonenumber;
	
	@Column(name = "clientemail",length = 255)
	private String clientemail;	
	
	@Column(name = "clientcity",length = 255)
	private String clientcity;	
	
	@Column(name = "clientward",length = 255)
	private String clientward;	
	
	@Column(name = "clientdistrict",length = 255)
	private String clientdistrict;
	
	@Column(name = "clientstreet",length = 255)
	private String clientstreet;	
	@Column(name = "productbuy",updatable = true,nullable = true)
	private int productbuy;
}
