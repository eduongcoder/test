package com.example.Entity;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseOderItemsDTO {
	private Integer purchase_order_items_id;

	private BigDecimal purchase_price;

	private Integer quantity;

	private Integer purchaseOrder;

	private Integer productVersion;
}
