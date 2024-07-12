package com.example.Entity;


import java.time.LocalDateTime;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDTOShopCart {
	private Integer order_items_id;

	private String product_name;

	private Integer product_price;
	
	private Integer base_price;

	private Integer quantity;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;


//	private Integer productVersion;
//	private List<VariantDTO> variant;
}
