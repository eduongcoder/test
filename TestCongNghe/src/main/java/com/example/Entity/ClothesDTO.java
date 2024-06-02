package com.example.Entity;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class ClothesDTO {

	@NonNull
	private Integer ClothesID;
	@NonNull
	private String ClothesName;
	@NonNull
	private Short ClothesQuantity;
	@NonNull
	private List<TypeOfClothesDTO> typeOfClothes;
	
	
}
