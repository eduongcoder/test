package com.example.Entity;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO  {
	private int product_id;

	private String name;

	private String description;

	private String state;

	private String materialProduct;

	private LocalDateTime created_at;

	private LocalDateTime updated_at;

	private Integer typeOfProductNew;

	private Integer typeOfProductGender;

	
	private List<ProductVersionDTO> productVersion;
	
}
