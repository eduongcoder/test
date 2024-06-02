package com.example.Entity;

import java.io.Serializable;
import java.util.List;

import com.example.Enum.TypeOfProductEnum;
import com.example.Enum.TypeOfProductGender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "typeofproduct")
@Data
@NoArgsConstructor
public class TypeOfProduct implements Serializable{
	private static final long serialVersionUID=1;
	
	@Column(name = "typeofproductid")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int typeofproductid;
	
	@Column(name = "typeofproduct",updatable = true)
	@Enumerated(EnumType.STRING)
	private TypeOfProductEnum typeofproduct;
	
	@Column(name = "typeofproductgender",updatable = true)
	@Enumerated(EnumType.STRING)
	private TypeOfProductGender typeofproductgender;
	
	@OneToMany(mappedBy  = "typeofproductid")
	private List<Product> Product ;
//	@OneToMany(mappedBy = "typeofproductid")
//	private List<Size> size;
}
