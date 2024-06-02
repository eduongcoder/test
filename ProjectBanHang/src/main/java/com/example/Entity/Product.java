package com.example.Entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

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
@Table(name = "product")
@Data
@NoArgsConstructor
public class Product implements Serializable{
private static final long serialVersionUID=1;
	
	@Column(name = "productid")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productid;
	
	@Column(name = "productname",length = 255,updatable = true)
	private String productname;
	
//	@Column(name = "productprice",updatable = true)
//	private int productprice;
	
	@Column(name = "productmaterial",length = 255,updatable = true)
	private String productmaterial;

//	@ManyToOne
//	@JoinColumn(name = "colorid")
//	private Color colorid;
	
//	@ManyToOne
//	@JoinColumn(name = "sizeeid")
//	private Size sizeeid;
	
	@OneToMany(mappedBy = "productid")
	private List<ImageProduct> image;
	
	@OneToMany(mappedBy = "productid")
	private List<Variant> variant; 
	
	@ManyToOne
	@JoinColumn(name = "typeofproductid")
	private TypeOfProduct typeofproductid;
	
//	@OneToMany(mappedBy = "productID")
//	private Set<ClientProduct> clientProduct;
	
	
}
