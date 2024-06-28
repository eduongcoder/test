package com.example.Entity;

import java.util.List;

import com.example.Enum.SizeEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VariantDTO {

	private int variants_id;

	private int quantity_in_stock;

	private boolean isDelete;
	
	private String color;

	private SizeEnum size;

	private Integer productversion;

	private List<ImagesDTO> images;
}
