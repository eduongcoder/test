package com.example.Entity;

import java.sql.Date;

import com.example.Enum.TypeOfProductEnum;
import com.example.Enum.TypeOfProductGender;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductOnlyDTO {
	private Integer product_id;

	private String name;

	private String description;
//
//	private TypeOfProductEnum type;
//
//	private TypeOfProductGender gender;

	private String state;

	private String materialProduct;

	private Date created_at;

	private Date updated_at;
}
