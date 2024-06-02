package com.example.Entity;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ColorDTO {
	private int id;

	private String color_name;

	private List<VariantDTO> variants;
}
