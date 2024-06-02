package com.example.Entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

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
@Table(name = "account")
@Data
@NoArgsConstructor
public class Account implements Serializable{
	private static final long serialVersionUID=1;
	@Column(name = "accountid")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountid;
	
	@Column(name = "accountgender")
	@Enumerated(EnumType.STRING)
	private Gender accountgender;
	
	@Column(name = "accountheight",updatable = true)
	private short accountheight;
	
	@Column(name = "accountweight",updatable =   true)
	private short accountweight;
	@Column(name = "accountemail",length = 255)
	private String accountemail;
	@Column(name = "accountphonenumber",length = 10)
	private String accountphonenumber;
	@Column(name = "accountdayofbrith",updatable =true )
	private Date accountdayofbrith;
	
	@Column(name = "accountusername")
	private String accountusername;
	
	@Column(name = "accountpassword")
	private String accountpassword;
	@OneToMany(mappedBy = "accountID")
	private Set<ClientProduct> clientProduct;
	
	
}
