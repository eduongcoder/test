package com.example.From;


import java.util.List;

import com.example.Entity.ProductDTO;
import com.example.Enum.TypeOfProductEnum;
import com.example.Enum.TypeOfProductGender;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TypeOfProductForm {
	
	private TypeOfProductEnum typeofproduct;

	private TypeOfProductGender typeofproductgender;

	private List<ProductDTO> Product;
}
