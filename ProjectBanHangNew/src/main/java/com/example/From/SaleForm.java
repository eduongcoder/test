package com.example.From;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaleForm {

	private Integer quantity;

	private Integer sale_price;
	
	private Integer sale_base_price;

	private Integer variant;

	private String sale_date_start;
	
	private String sale_date_end;
}
