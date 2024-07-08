package com.example.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DiscountOnlyDTO {
	private Integer discount_id;

	private String title;

	private BigDecimal percent;

	private LocalDateTime date_start;

	private LocalDateTime date_end;

	private String status;
}
