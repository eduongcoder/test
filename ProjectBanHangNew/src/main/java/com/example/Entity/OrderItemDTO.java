package com.example.Entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDTO {
	private Integer order_items_id;

	private String product_name;

	private Integer product_price;

	private Integer quantity;
	
	private Integer productVersion;
	
	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

//	private List<SalesDTO> sales;

	private Integer orders;

	private Integer idvariant;

	private VariantDTO variants;

}
