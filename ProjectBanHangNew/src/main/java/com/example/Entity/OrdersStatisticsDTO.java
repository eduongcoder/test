package com.example.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrdersStatisticsDTO {
	private int idOrder;
	private int totalMoney;
	private int idAccount;
}
