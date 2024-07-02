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
@Table(name = "sales")
@Data
@NoArgsConstructor
public class Sales implements Serializable{
	private static final long serialVersionUID=1;
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "quantity",updatable = true)
	private int quantity;
	
	@Column(name = "sale_price",updatable = true)
    private int sale_price;
	
	@Column(name = "sale_base_price",updatable = true)
	private int sale_base_price;
	
	@Column(name = "isdelete", columnDefinition = "BOOLEAN DEFAULT false")
	private boolean isdelete;
	
	@Column(name = "date_create", updatable = false ,insertable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime date_create;
	
	@Column(name = "date_update", updatable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private LocalDateTime date_update;
	
	@Column(name = "sale_date_start",updatable = true)
	private LocalDateTime sale_date_start;
	
	@Column(name = "sale_date_end",updatable = true)
	private LocalDateTime sale_date_end;
	
	@ManyToOne
	@JoinColumn(name ="variant_id")
	private Variant variant_id;
	
}
