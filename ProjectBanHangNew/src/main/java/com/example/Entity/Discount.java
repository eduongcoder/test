package com.example.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "discount")
@Entity
@Data
@NoArgsConstructor
public class Discount implements Serializable {
	private static final long serialVersionUID=1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int discount_id;
	
	@Column(name = "title",length = 100,updatable = true)
	private String title;
	
	@Column(name = "percent",precision = 3, scale = 2)
	private BigDecimal percent;
	
	@Column(name = "date_start",updatable = true)
	private LocalDateTime date_start;
	
	@Column(name = "date_end",updatable = true)
	private LocalDateTime date_end;
	
	@Column(name = "status",length = 20)
	private String status;
	
	@OneToMany(mappedBy = "discount")
	private List<SaleDiscount> saleDiscount;
}
