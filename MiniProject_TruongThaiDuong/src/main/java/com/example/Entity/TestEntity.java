package com.example.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "test")
@Entity
@Data
@NoArgsConstructor
public class TestEntity {
	
	@Column(name = "idtest")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idtest;
	
	@Column(name = "chuoi")
	private byte[] chuoi;
}
