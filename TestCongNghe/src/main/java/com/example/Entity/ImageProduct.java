package com.example.Entity;


import java.io.Serializable;

import com.example.Enum.ImageEnum;

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
@Table(name = "image")
@Data
@NoArgsConstructor
public class ImageProduct implements Serializable{
private static final long serialVersionUID=1;
	
	@Column(name = "imageid")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int imageid;
	
	@Column(name = "imagetype",updatable = true)
	@Enumerated(EnumType.STRING)
	private ImageEnum imagetype;
	
	@Column(name = "imageupload",updatable = true)
	private byte[] imageupload;
	
	@ManyToOne
	@JoinColumn(name = "productid")
	private Product productid;
}
