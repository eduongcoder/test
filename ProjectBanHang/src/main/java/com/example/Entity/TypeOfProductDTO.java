package com.example.Entity;

import java.util.List;

import com.example.Enum.TypeOfProductEnum;
import com.example.Enum.TypeOfProductGender;
 
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
@Data
@NoArgsConstructor
public class TypeOfProductDTO {
	@NonNull
	private Integer typeofproductid;
	@NonNull
	private TypeOfProductEnum typeofproduct;
	@NonNull
	private TypeOfProductGender typeofproductgender;

//	@NonNull
//	private List<SizeDTO> size;
	
//	@NonNull
//	private Integer productid;
}
