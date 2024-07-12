package com.example.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product implements Serializable {
	private static final long serialVersionUID = 1;

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int product_id;

	@Column(name = "name", length = 255, updatable = true)
	private String name;

	@Column(name = "description", length = 255, updatable = true)
	private String description;

	@Column(name = "state", length = 50, updatable = true)
	private String state;

	@Column(name = "materialProduct", length = 255, updatable = true)
	private String materialProduct;

	@Column(name = "created_at", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime created_at;

	@Column(name = "updated_at", updatable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private LocalDateTime updated_at;

	@OneToMany(mappedBy = "product")
	private List<ProductVersion> productVersion;

	@ManyToOne
	@JoinColumn(name = "typeofproductnewid")
	private TypeOfProductNew typeOfProductNew;
	
	
	@ManyToOne
	@JoinColumn(name = "typeofproductgenderid")
	private TypeOfProductGender typeOfProductGender;
	
	
	@OneToMany(mappedBy = "catetoryProduct")
	private List<Category> categories;
	
	@OneToMany(mappedBy = "productid")
	private List<Images> imagesMap;

	@OneToMany(mappedBy = "product")
	private List<OrderItem> orderItems;
	
	@OneToMany(mappedBy = "product_id")
	private List<PersonFix> personFixs;

}
