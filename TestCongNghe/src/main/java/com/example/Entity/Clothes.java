package com.example.Entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Clothes")
@Data
@NoArgsConstructor
public class Clothes implements Serializable{
	private static final long serialVersionUID=1;
	
	@Column(name = "ClothesID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "ClothesName",length = 255,updatable = true)
	private String clothesName;
	
	@Column(name = "ClothesQuantity",updatable = true)
	private short clothesQuantity;
	
	@OneToMany(mappedBy = "clothes")
	private List<TypeOfClothes> typeOfClothes;
}
