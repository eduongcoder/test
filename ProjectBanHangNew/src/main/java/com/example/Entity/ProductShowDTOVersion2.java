package com.example.Entity;

import java.time.LocalDateTime;
import java.util.List;

import com.example.Enum.TypeOfProductEnum;
import com.example.Enum.TypeOfProductGenderEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductShowDTOVersion2 {
	private Integer product_id;

	private String name;

	private String description;

	private TypeOfProductEnum type;

	private TypeOfProductGenderEnum gender;

	private String state;

	private String materialProduct;

	private LocalDateTime created_at;

	private LocalDateTime updated_at;

	private List<ProductVersionDTOOnlyStock> productVersion;

	private List<CategoryDTO> categories;
	
	private List<PersonFixDTO> personFixs;
	
	private List<ImagesDTO> imagesMap;
}
