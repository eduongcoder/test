package com.example.Entity;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "price")
@Data
@NoArgsConstructor
public class Price implements Serializable{
	private static final long serialVersionUID=1;
	
	@Column(name = "priceid")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int priceid;
	
	@Column(name = "priceroot",updatable = true)
	private int priceroot;
	
	@Column(name = "pricenomal",updatable = true)
	private int pricenomal;
	
	@Column(name = "pricesale",updatable = true)
	private int pricesale;
	
	@Column(name = "datesalestart",updatable = true)
	private Date datesalestart;
	
	@Column(name = "datesaleend",updatable = true)
	private Date datesaleend;
	
	@ManyToOne
	@JoinColumn(name = "productid")
	private Product productid;
	
}
