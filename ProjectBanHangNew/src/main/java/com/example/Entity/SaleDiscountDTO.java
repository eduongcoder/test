package com.example.Entity;


import com.example.Enum.SizeEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaleDiscountDTO {

	private Integer sales;

	private Integer discount;
	
	private Integer quantity;
	
	private Integer priceBase;
	
	private Integer priceSale;
	
	private String color;
	
	private SizeEnum size;
	
    private Integer variant;

    private Integer totalMoney;

	private Integer productID;
	
	private DiscountOnlyDTO discountshow;
}
