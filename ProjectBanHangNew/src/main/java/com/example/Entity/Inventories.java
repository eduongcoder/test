package com.example.Entity;



import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import org.aspectj.weaver.ast.Or;

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
@Table(name = "inventories")
@Data
@NoArgsConstructor
public class Inventories implements Serializable {
	private static final long serialVersionUID=1;
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int inventory_id;
	
	@Column(name = "change_amount")
	private int change_amount;
	
	@Column(name = "event_type",length = 50, updatable = true)
    private String event_type;
	
	@Column(name = "order_id")
	private int order_id;
	
	@Column(name = "amount")
	private int amount;
	
	@Column(name = "event_date",updatable = true)
	private LocalDateTime event_date;

	@ManyToOne
	@JoinColumn(name = "variant_id")
	private Variant inventoryVariant;
}
