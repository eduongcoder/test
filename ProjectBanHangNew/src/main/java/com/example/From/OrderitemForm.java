package com.example.From;


import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class OrderitemForm {
	private int order_items_id;
	
	private String product_name;
	
    private Integer product_price;
    
    private Integer price_base;

	private Integer quantity;
	
	private Integer productID;
	
	private Integer sizeID;
	
	private Integer colorID;

	private LocalDateTime createAt;
	

	
}