package com.example.Entity;


import com.example.Enum.TypeOfProductEnum;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TypeOfProductNewDTO {
	private int typeofproductnew_id;
	
	

	private TypeOfProductEnum typeofproduct;
	

//	private List<ProductDTO> products;
}
