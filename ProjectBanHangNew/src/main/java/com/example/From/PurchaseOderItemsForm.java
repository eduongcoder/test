package com.example.From;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseOderItemsForm {
	private Integer purchase_order_items_id;

	private Integer purchase_price;

	private Integer quantity_real;
	
	private Integer quantity;

	private Integer purchaseOrder;

	private Integer variant;

	private Integer productVersion;
}
