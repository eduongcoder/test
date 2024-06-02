package com.example.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "sales")
@Data
@NoArgsConstructor
public class Sales implements Serializable{
	private static final long serialVersionUID=1;
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "quantity",updatable = true)
	private int quantity;
	
	@Column(name = "sale_price", precision = 10, scale = 2,updatable = true)
    private BigDecimal sale_price;
	
	@Column(name = "sale_date",updatable = true)
	private Date sale_date;
	
	@ManyToOne
	@JoinColumn(name ="version_product_id")
	private ProductVersion productVersion;
	
	@ManyToOne
	@JoinColumn(name = "order_item_id")
	private OrderItem orderitem;
	
}
