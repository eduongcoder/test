package com.example.From;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductVersionForm {

	private String version_name;

	private Integer price;

	private Integer quantity_in_stock;

	private Boolean isDelete;

	private Integer product;
	
	

}
