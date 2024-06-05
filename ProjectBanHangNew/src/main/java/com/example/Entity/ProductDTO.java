package com.example.Entity;

import java.sql.Date;
import java.util.List;

import com.example.Enum.SizeEnum;
import com.example.Enum.TypeOfProductEnum;
import com.example.Enum.TypeOfProductGender;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class ProductDTO  {
	private int product_id;

	private String name;

	private String description;

	private String state;

	private String materialProduct;

	private Date created_at;

	private Date updated_at;

	private List<ProductVersionDTO> productVersion;

	private Integer typeofproduct;

	
}