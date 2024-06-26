package com.example.Entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrdersDTO {
	private int orders_id;
	
    private Integer total_amount;
	
	private String status;
	
	private LocalDateTime created_at;
	
	private LocalDateTime updated_at;
	
	private Integer account;
	
	private String addressorder;
	
	private List<OrderItemDTOVariant> orderItems;
//	private List<OrderItemDTO> orderItems;
}
