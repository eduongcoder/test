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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "purchase_orders")
@Data
@NoArgsConstructor
public class PurchaseOrders implements Serializable {
	private static final long serialVersionUID = 1;
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int purchase_orders_id;

	@Column(name = "supplier_id", updatable = true)
	private int supplier_id;
	
	@Column(name = "order_date", updatable = true)
	private Date order_date;
	
	@Column(name = "total_amount", precision = 10, scale = 2,updatable = true)
    private BigDecimal total_amount;
    
	@Column(name = "status", length = 50,updatable = true)
	private String status;

	@OneToMany(mappedBy = "purchaseOrder")
	private List<PurchaseOderItems> purchaseorderitem;
	 
}
