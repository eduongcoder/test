package com.example.Entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import com.example.Enum.TypeOfProductEnum;
import com.example.Enum.TypeOfProductGender;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductShowDTO {
	private Integer product_id;

	private String name;

	private String description;

	private TypeOfProductEnum type;

	private TypeOfProductGender gender;

	private String state;

	private String materialProduct;

	private LocalDateTime created_at;

	private LocalDateTime updated_at;

	private List<ProductVersionShowDTO> productVersion;

	private List<PersonFixDTO> personFixs;
}
