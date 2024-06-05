package com.example.Entity;

import java.util.List;

import com.example.Enum.TypeOfProductEnum;
import com.example.Enum.TypeOfProductGender;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class TypeOfProductDTO {
	private int id;

	private TypeOfProductEnum typeofproduct;

	private TypeOfProductGender typeofproductgender;

	private List<Product> Product;

}