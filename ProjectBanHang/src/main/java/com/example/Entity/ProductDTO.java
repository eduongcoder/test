package com.example.Entity;

import java.util.List;

import com.example.Enum.SizeEnum;
import com.example.Enum.TypeOfProductEnum;
import com.example.Enum.TypeOfProductGender;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class ProductDTO  {
	@NonNull
	private Integer productid;
	@NonNull
	private String productname;

//	private Integer productprice;
//	@NonNull
//	private String productmaterial;
//
//	private String color;
//	
//
//	private SizeEnum sizee;
//	@NonNull
	private List<ImageProductDTO> image;
	
	private List<VariantDTO> variant; 
//	@NonNull
	private TypeOfProductEnum typeofproduct;
	

	private TypeOfProductGender gender;

	
}
