package com.example.Entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductVersionShowDTO {
	private Integer productVersion_id;
    
    private Integer quantity_in_stock;

    private Boolean isDelete;
	
	private Date dateDelete;
	
	private LocalDateTime created_at;
	
	private LocalDateTime updated_at;
	
	private Integer product;
	
	private List<VariantDTO> variants;
	
	
}
