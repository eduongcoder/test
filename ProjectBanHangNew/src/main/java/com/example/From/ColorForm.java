package com.example.From;

import java.util.List;

import com.example.Entity.VariantDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ColorForm {
	private String color_name;

	private List<VariantDTO> variants;
}
