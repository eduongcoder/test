package com.example.From;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryForm {
	private Integer category_id;

	private Integer price_base;

	private Integer catetoryProduct;

	private Integer catetoryColor;

	private Boolean isdelete;	
	
	private Integer catetorySize;
}
