package com.example.From;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductVersionForm {

	private Integer productVersion_id;
	
	private String version_name;

	private Integer quantity_in_stock;

	private Integer productID;
	
	

}
