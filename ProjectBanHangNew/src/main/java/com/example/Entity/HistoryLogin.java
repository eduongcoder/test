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

@Table(name = "history_login")
@Entity
@Data
@NoArgsConstructor
public class HistoryLogin implements Serializable {
	private static final long serialVersionUID=1;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int history_login_id;
	
	@Column(name = "datelogin", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime datelogin;
	
	@Column(name = "note", length = 100)
	private String note;
	
	@ManyToOne
	@JoinColumn(name = "id_account")
	private Account id_account;
}
