package com.example.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "TypeOfClothes")
@NoArgsConstructor
public class TypeOfClothes implements Serializable{
	private static final long serialVersionUID=1;
	
	@Column(name = "TypeOfClothes")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "TypeName",length = 255,updatable = true)
	private String typeName;
	
	@ManyToOne
	@JoinColumn(name = "ClothesID")
	private Clothes clothes;
}
