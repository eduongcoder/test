package com.example.From;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import com.example.Entity.Account;
import com.example.Entity.Inventories;
import com.example.Entity.OrderItem;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrdersForm {

	private int orders_id;

	private BigDecimal total_amount;

	private String status;

	private LocalDateTime created_at;

	private LocalDateTime updated_at;

	private Account account;

//	private List<Inventories> inventories;

	private List<OrderItem> orderItems;
}
