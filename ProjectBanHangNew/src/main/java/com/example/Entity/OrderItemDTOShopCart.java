package com.example.Entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDTOShopCart {
	private Integer order_items_id;

	private String product_name;

	private BigDecimal product_price;

	private Integer quantity;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

//	private Integer orders;

	private Integer productVersion;
//	private List<VariantDTO> variant;
}
