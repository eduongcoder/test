package com.example.Entity;

import java.io.Serializable;

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
@Table(name="Relatives")
@Data
@NoArgsConstructor
public class Relative implements Serializable{
private static final long serialVersionUID=1l;
	
	@Column(name="RelativesID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="RelativesName",length = 50,updatable = true)
	private String name;
	
	@Column(name="RelativesAge",updatable = true)
	private short age;
	
	@ManyToOne
	@JoinColumn(name="EmployeeID")
	private Employee idEmployee;

}
