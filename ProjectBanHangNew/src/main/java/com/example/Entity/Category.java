package com.example.Entity;

import java.io.Serializable;
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
@Table(name = "category")
@Data
@NoArgsConstructor
public class Category implements Serializable {
	private static final long serialVersionUID = 1;
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int category_id;
	
	@Column(name = "price_base")
	private int price_base;
	
	@Column(name = "date_create", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime date_create;
	
	@Column(name = "isdelete", columnDefinition = "BOOLEAN DEFAULT false")
	private boolean isdelete;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product catetoryProduct;
	
	@ManyToOne
	@JoinColumn(name = "color_id")
	private Color catetoryColor;
	
	@ManyToOne
	@JoinColumn(name = "size_id")
	private Size catetorySize;
	
	@OneToMany(mappedBy = "category")
	private List<HistoryCategory> historyCategories;
	

}
