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
@Table(name = "history_category")
@Data
@NoArgsConstructor
public class HistoryCategory implements Serializable {
	private static final long serialVersionUID=1;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int history_category_id;
	
	@Column(name = "old_price")
	private int old_price;
	
	@Column(name = "new_price")
	private int new_price;

	@Column(name = "date_update", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime date_update;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
}
