package com.example.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemStatisticDTO {
//	private int idOrderItem;
	private int money;
	private int baseMoney;
	private int quantity;
	private String sizeColor;
}
