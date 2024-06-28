package com.example.Entity;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SalesDTO {
	private Integer id;

	private Integer quantity;

	private Integer sale_price;

	private LocalDateTime date_create;
	
	private LocalDateTime date_update;
	
	private LocalDateTime sale_date_start;
	
	private LocalDateTime sale_date_end;

	private Integer category;
	
	
//	private Integer orderitem;
}
