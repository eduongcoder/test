package com.example.From;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.example.Entity.Orders;
import com.example.Entity.ProductVersion;
import com.example.Entity.Sales;
import com.example.Entity.SalesDTO;
import com.example.Entity.Variant;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class OrderitemForm {
	private int order_items_id;
	
	private String product_name;
	
    private BigDecimal product_price;
    
	private Integer productVersion;

	private int quantity;
	
	private LocalDateTime created_at;
	
	private LocalDateTime updated_at;
	
	private List<Sales> sales;
	
	private Integer orders;
	
}