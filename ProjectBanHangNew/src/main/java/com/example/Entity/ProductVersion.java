package com.example.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
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
@Table(name = "product_versions")
@Data
@NoArgsConstructor
public class ProductVersion implements Serializable {
	private static final long serialVersionUID = 1;
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productVersion_id;
	
	
	@Column(name = "version_name" ,length = 255,updatable = true)
	private String version_name;
	@Column(name = "price", precision = 10, scale = 2,updatable = true)
    private BigDecimal price;

	@Column(name = "quantity_in_stock",updatable = true)
    private int quantity_in_stock;

	@Column(name = "isDelete",updatable = true)
    private Boolean isDelete;
	
	@Column(name = "dateDelete",updatable = true)
	private Date dateDelete;
	
	@Column(name = "created_at",updatable = true)
	private Date created_at;
	
	@Column(name = "updated_at",updatable = true)
	private Date updated_at;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@OneToMany(mappedBy = "productversion")
	private List<Variant> variants;
	
	@OneToMany(mappedBy = "productVersion")
	private List<PurchaseOderItems> purchaseOderItems;
	
	@OneToMany(mappedBy = "productVersion")
	private List<Inventories> inventories;
	
	@OneToMany(mappedBy = "productVersion")
	private List<OrderItem> orderItems;
	
	@OneToMany(mappedBy = "productVersion")
	private List<Sales> sales;
}
