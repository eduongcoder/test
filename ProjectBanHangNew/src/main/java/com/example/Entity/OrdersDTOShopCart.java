package com.example.Entity;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrdersDTOShopCart {
	private int orders_id;

	private Integer account;

	private List<OrderItemDTOShopCart> orderItems;
	private Integer idvariant;
}
