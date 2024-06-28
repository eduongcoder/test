package com.example.Entity;

import java.io.Serializable;
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
@Table(name = "variants")
@Data
@NoArgsConstructor
public class Variant implements Serializable {
	private static final long serialVersionUID = 1;

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int variants_id;

	@Column(name = "quantity_in_stock", updatable = true)
	private int quantity_in_stock;

	@Column(name = "isDelete")
	private boolean isDelete;
	

	
	@ManyToOne
	@JoinColumn(name = "color_id")
	private Color color;

	@ManyToOne
	@JoinColumn(name = "size_id")
	private Size size;

	@ManyToOne
	@JoinColumn(name = "version_product_id")
	private ProductVersion productversion;

	@OneToMany(mappedBy = "inventoryVariant")
	private List<Inventories> inventories;

	@OneToMany(mappedBy = "variant")
	private List<Images> images;
}
