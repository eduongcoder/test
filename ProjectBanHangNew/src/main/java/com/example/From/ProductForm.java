package com.example.From;

import java.util.List;

import com.example.Entity.Images;
import com.example.Enum.SizeEnum;
import com.example.Enum.TypeOfProductEnum;
import com.example.Enum.TypeOfProductGender;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductForm {
	private int productid;
	private String productname;
	private int productprice;
	private String productmaterial;
	private String color;
	private SizeEnum sizee;

	private List<Images> image;
	private TypeOfProductEnum typeofproduct;
	private TypeOfProductGender gender;

}
