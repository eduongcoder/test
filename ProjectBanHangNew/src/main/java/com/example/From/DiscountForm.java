package com.example.From;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DiscountForm {
	private Integer discount_id;
	
	private String title;
	
	private BigDecimal percent;
	
	private LocalDate date_start;
	
	private LocalDate date_end;
}
