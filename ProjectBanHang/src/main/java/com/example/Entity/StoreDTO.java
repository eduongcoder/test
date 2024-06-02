package com.example.Entity;

import java.util.List;

import com.example.Enum.SizeEnum;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StoreDTO {
	private int storeid;
	

	private SizeEnum productsize;
	
	private String color;
	
	private int productquantity;
	
	private int priceroot;
	
	private List<Variant> variant;
}
