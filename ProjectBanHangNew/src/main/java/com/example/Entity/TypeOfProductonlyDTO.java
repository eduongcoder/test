package com.example.Entity;

import com.example.Enum.TypeOfProductEnum;
import com.example.Enum.TypeOfProductGender;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TypeOfProductonlyDTO {
	private int typeofproduct_id;

	private TypeOfProductEnum typeofproduct;

	private TypeOfProductGender typeofproductgender;
}
