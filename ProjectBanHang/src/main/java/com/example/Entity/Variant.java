package com.example.Entity;

import java.io.Serializable;
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
@Table(name = "variant")
@Data
@NoArgsConstructor
public class Variant implements Serializable{
	private static final long serialVersionUID=1;
	
	@Column(name = "variantid")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int variantid;
	
	@ManyToOne
	@JoinColumn(name = "storeid")
	private Store storeid;
	
	@ManyToOne
	@JoinColumn(name = "productid")
	private Product productid;
	@OneToMany(mappedBy = "variantID")
	private Set<ClientProduct> clientProduct;
}
