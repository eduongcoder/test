package com.example.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "order_items")
@Data
@NoArgsConstructor
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1;

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int order_items_id;

	@Column(name = "product_name", length = 255, updatable = true)
	private String product_name;

	@Column(name = "product_price", precision = 10, scale = 2, updatable = true)
	private BigDecimal product_price;

	@Column(name = "quantity", updatable = true)
	private int quantity;

	@Column(name = "created_at", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createdAt;

	@Column(name = "updated_at",   columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private LocalDateTime updatedAt;

	@OneToMany(mappedBy = "orderitem")
	private List<Sales> sales;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Orders orders;

	@ManyToOne
	@JoinColumn(name = "version_product_id")
	private ProductVersion productVersion;
}