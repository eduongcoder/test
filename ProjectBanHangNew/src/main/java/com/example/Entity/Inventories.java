package com.example.Entity;



import java.io.Serializable;
import java.sql.Date;

import org.aspectj.weaver.ast.Or;

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
@Table(name = "inventories")
@Data
@NoArgsConstructor
public class Inventories implements Serializable {
	private static final long serialVersionUID=1;
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int inventory_id;
	
//	@Column(name = "version_product_id")
//	private int version_product_id;
	
	@Column(name = "change_amount")
	private int change_amount;
	
	@Column(name = "event_type",length = 50, updatable = true)
    private String event_type;
	
	@Column(name = "event_date",updatable = true)
	private Date event_date;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Orders orders;
	
	@ManyToOne
	@JoinColumn(name = "version_product_id")
	private ProductVersion productVersion;

}
