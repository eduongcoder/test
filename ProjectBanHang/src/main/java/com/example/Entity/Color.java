//package com.example.Entity;
//
//import java.io.Serializable;
//import java.util.List;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.Table;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Table(name = "color")
//@Data
//@NoArgsConstructor
//public class Color implements Serializable {
//	private static final long serialVersionUID=1;
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "colorid")
//	private int colorid;
//	
//	@Column(name = "color",length = 30,updatable = true)
//	private String color;
//	
//	@OneToMany(mappedBy = "colorid")
//	private List<Product> products;
//}
