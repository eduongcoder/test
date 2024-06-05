package com.example.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.Enum.SizeEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VariantNoAccountDTO {
private int variants_id;
	
	private int quantity_in_stock;
	
	private String color;
	
	private SizeEnum size;
	
	private int quantity;
	
	private Integer productversion;
	
	
	
	private BigDecimal price;
	
	private String productversionName;
	
	private List<ImagesDTO> images;
	
	private LocalDateTime createTime;
}