package com.example.Entity;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseOderItemsDTO {
	private Integer purchase_order_items_id;

	private Integer purchase_price;

	private Integer quantity;

	private Integer quantity_real;

	private Integer purchaseOrder;

	private Integer variant;

	private Integer productVersion;
}
