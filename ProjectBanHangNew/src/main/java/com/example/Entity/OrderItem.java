package com.example.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

	@Column(name = "product_price", updatable = true)
	private int product_price;

	@Column(name = "base_price", updatable = true)
	private int base_price;
	
	@Column(name = "quantity", updatable = true)
	private int quantity;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at", updatable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private LocalDateTime updatedAt;

	@ManyToOne
	@JoinColumn(name  = "product_id")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Orders orders;
	
	@ManyToOne
	@JoinColumn(name = "color_id")
	private Color color;

	@ManyToOne
	@JoinColumn(name = "size_id")
	private Size size;

}
