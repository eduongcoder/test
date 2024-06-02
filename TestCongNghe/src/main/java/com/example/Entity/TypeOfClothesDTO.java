package com.example.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class TypeOfClothesDTO {
	@NonNull
	private Integer id;
	@NonNull
	private String typeName;
	@NonNull
	private Integer ClothesID;
}
