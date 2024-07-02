package com.example.Entity;


import com.example.Enum.TypeOfProductGenderEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TypeOfProductGenderDTO {
	private int typeofproductgender_id;

	private TypeOfProductGenderEnum typeofproductgender;

//	private List<ProductDTO> products;
}
