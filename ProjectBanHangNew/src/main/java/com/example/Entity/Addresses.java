package com.example.Entity;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

import com.example.Enum.Gender;

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
@Table(name = "addresses")
@Data
@NoArgsConstructor
public class Addresses implements Serializable {
	private static final long serialVersionUID = 1;

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account_id;

	@Column(name = "city", length = 100, updatable = true)
	private String city;

	@Column(name = "state", length = 100, updatable = true)

	private String state;

	@Column(name = "country", length = 100, updatable = true)

	private String country;

	@Column(name = "created_at", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime created_at;
	
	@Column(name = "updated_at", updatable = true,   columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private LocalDateTime updated_at;
	
}
