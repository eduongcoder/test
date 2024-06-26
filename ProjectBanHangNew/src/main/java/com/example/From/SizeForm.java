package com.example.From;

import java.util.List;

import com.example.Entity.VariantDTO;
import com.example.Enum.SizeEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SizeForm {
	
	private SizeEnum size_name;
	
	private List<VariantDTO> variants;
}
