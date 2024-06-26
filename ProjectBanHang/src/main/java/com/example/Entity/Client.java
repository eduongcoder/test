package com.example.Entity;


import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
public class Client implements Serializable{
	private static final long serialVersionUID=1;
	
	@Column(name = "clientid")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clientid;
	
	@Column(name = "clientname",length = 255,updatable = true)
	private String clientname;
	
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
	
//	
//	@OneToOne
//    @JoinColumn(name = "account_id", referencedColumnName = "accountid")
//	private Account account;
}
