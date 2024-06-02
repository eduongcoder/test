package com.example.Entity;

import com.example.Enum.SizeEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VariantDTO {

	private int variantid;

	private Integer storeid;
	private SizeEnum size;
	private String color;
	private int quantity;
	private int priceroot;
	private Integer productid;
}
