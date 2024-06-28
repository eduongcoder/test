package com.example.Entity;

import java.time.LocalDateTime;
import java.util.List;

import com.example.Enum.SizeEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDTO {
	private Integer category_id;

	private Integer price_base;

	private LocalDateTime date_create;

	private Integer catetoryProduct;

	private Integer catetoryColor;

	private Integer catetorySize;

	private String color;

	private SizeEnum sizeEnum;
	
	private List<HistoryCategoryDTO> historyCategories;

}
