package com.example.Entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="test")
@Data
@NoArgsConstructor
public class Test implements Serializable{
	private static final long serialVersionUID=1;
	
	@Column(name="id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "imageName",length = 255,updatable = true)
	private String imageName;
	
	@Column(name="imagePrice",updatable = true)
	private int imagePrice;
	
	@Column(name="image",updatable = true)
	private byte[] image;
}
