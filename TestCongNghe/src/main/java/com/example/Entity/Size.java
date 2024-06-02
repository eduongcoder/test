package com.example.Entity;

import java.io.Serializable;

import com.example.Enum.SizeEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "size")
@Data
@NoArgsConstructor
public class Size implements Serializable{
	private static final long serialVersionUID=1;
	
	
	@Column(name = "sizeeid")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sizeeid;
	
	@Column(name = "productsize",updatable = true)
	@Enumerated(EnumType.STRING)
	private SizeEnum productsize;
	
	
	@ManyToOne
	@JoinColumn(name = "productid")
	private Product productid;
}
