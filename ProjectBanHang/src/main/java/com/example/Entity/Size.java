//package com.example.Entity;
//
//import java.io.Serializable;
//import java.util.List;
//
//import com.example.Enum.SizeEnum;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.Table;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Table(name = "size")
//@Data
//@NoArgsConstructor
//public class Size implements Serializable{
//	private static final long serialVersionUID=1;
//	
//	
//	@Column(name = "sizeeid")
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int sizeeid;
//	
//	
//	
//	@OneToMany(mappedBy = "sizeeid")
//	private List<Product> products;
////	
////	@Column(name = "color",length = 255,updatable = true)
////	private String color;
////	
////	@Column(name = "productquantity",updatable = true)
////	private int productquantity;
////	@ManyToOne
////	@JoinColumn(name = "typeofproductid")
////	private TypeOfProduct typeofproductid;
//}
