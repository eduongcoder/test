package com.example.Entity;

import java.time.LocalDateTime;

import com.example.Enum.TypeOfProductEnum;
import com.example.Enum.TypeOfProductGenderEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductShowDTOVersion3 {
	private Integer product_id;

	private String name;

	private String description;

	private TypeOfProductEnum type;

	private TypeOfProductGenderEnum gender;

	private String state;

	private String materialProduct;

	private LocalDateTime created_at;

	private LocalDateTime updated_at;
}
