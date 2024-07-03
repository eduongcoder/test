package com.example.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
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
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Orders implements Serializable{
	private static final long serialVersionUID =1;
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orders_id;
	
	@Column(name = "total_amount", precision = 10, scale = 2,updatable = true)
    private int total_amount;
	
	@Column(name = "status",length = 50,updatable = true)
	private String status;
	@Column(name = "addressorder",length = 100,updatable = true)
	private String addressorder;
	
	@Column(name = "shipping_at")
	private LocalDateTime shipping_at;
	
	@Column(name = "complete_at")
	private LocalDateTime complete_at;
	
	@Column(name = "created_at", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime created_at;

	@Column(name = "updated_at",   columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private LocalDateTime updated_at;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;
	
	@OneToMany(mappedBy = "orders")
	private List<Inventories> inventories; 
	
	@OneToMany(mappedBy = "orders")
	private List<OrderItem> orderItems;
}
