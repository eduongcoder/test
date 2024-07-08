package com.example.Entity;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InventoriesReasonDTO {
	private int idInventory;
	private int idproduct;
	private int amount;
	private int change_amount;
	private int idVariant;
	private String sizeColor;
	private String reason;
}
