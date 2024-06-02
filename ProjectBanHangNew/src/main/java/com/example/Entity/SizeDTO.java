package com.example.Entity;

import java.util.List;

import com.example.Enum.SizeEnum;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SizeDTO {
	private int id;
	
	private SizeEnum size_name;
	
	private List<VariantDTO> variants;
}
