package com.example.Entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PriceDTO {
private int priceid;
	
	private int priceroot;
	
	private int pricenomal;
	
	private int pricesale;
	
	private Date datesalestart;
	
	private Date datesaleend;
	

	private Integer productid;
	
}
