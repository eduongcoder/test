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

@Table(name = "typeofproductnew")
@Entity
@Data
@NoArgsConstructor
public class TypeOfProductNew implements Serializable{
	private static final long serialVersionUID =1;
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int typeofproductnew_id;
	
	
	@Column(name = "typeofproduct",updatable = true)
	@Enumerated(EnumType.STRING)
	private TypeOfProductEnum typeofproduct;
	
	@OneToMany(mappedBy = "typeOfProductNew")
	private List<Product> products;
}
