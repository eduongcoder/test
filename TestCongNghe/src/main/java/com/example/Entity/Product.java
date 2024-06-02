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
	
	@Column(name = "productprice",updatable = true)
	private int productprice;
		
	
	@Column(name = "productmaterial",length = 255,updatable = true)
	private String productmaterial;
	
	@Column(name = "productquantity",length = 255,updatable = true)
	private String productquantity;
	
	@OneToMany(mappedBy = "productid")
	private List<ImageProduct> image;
	
	@OneToMany(mappedBy = "productid")
	private List<Size> size;

}
