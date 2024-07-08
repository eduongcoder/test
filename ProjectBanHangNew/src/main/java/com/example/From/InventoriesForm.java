package com.example.From;



import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InventoriesForm {
	private int inventory_id;

	private int change_amount;
	private int amount;
	
	private String event_type;

	private Integer order_id;

	private Integer inventoryVariant;
}
