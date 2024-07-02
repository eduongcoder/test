package com.example.Entity;

import java.io.Serializable;
import java.util.List;

import com.example.Enum.TypeOfProductEnum;
import com.example.Enum.TypeOfProductGenderEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "typeofproductgender")
@Entity
@Data
@NoArgsConstructor
public class TypeOfProductGender implements Serializable{
	private static final long serialVersionUID =1;
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int typeofproductgender_id;
	
	@Column(name = "productgender")
	@Enumerated(EnumType.STRING) 
	private TypeOfProductGenderEnum typeOfProductGender;
	
	@OneToMany(mappedBy = "typeOfProductGender")
	private List<Product> products;
}
