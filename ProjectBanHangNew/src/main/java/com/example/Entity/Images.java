package com.example.Entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Base64;

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
@Table(name = "images")
@Data
@NoArgsConstructor
public class Images implements Serializable {
	private static final long serialVersionUID = 1;

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int images_id;

	@Column(name = "image_url", updatable = true)
	private String image_url;

	@Column(name = "created_at", updatable = true)
	private Date created_at;

	@Column(name = "updated_at", updatable = true)
	private Date updated_at;

	@ManyToOne
	@JoinColumn(name = "variant_id")
	private Variant variant;

}
