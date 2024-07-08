package com.example.Entity;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InventoriesDTO {
	private int inventory_id;

	private int change_amount;

	private int amount;

	
	private String event_type;

	private LocalDateTime event_date;

	private Integer order_id;

	private Integer inventoryVariant;
}
