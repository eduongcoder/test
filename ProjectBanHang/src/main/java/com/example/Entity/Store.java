package com.example.Entity;

import java.io.Serializable;
import java.util.List;

import com.example.Enum.SizeEnum;

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

@Entity
@Table(name = "store")
@Data
@NoArgsConstructor
public class Store implements Serializable{
	private static final long serialVersionUID=1;

	@Column(name = "storeid")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int storeid;
	
	@Column(name = "productsize",updatable = true)
	@Enumerated(EnumType.STRING)
	private SizeEnum productsize;
	
	@Column(name = "color",length = 30,updatable = true)
	private String color;
	
	@Column(name = "productquantity")
	private int productquantity;
	
	@Column(name = "priceroot")
	private int priceroot;
	
	@OneToMany(mappedBy = "storeid")
	private List<Variant> variant;
	

}
