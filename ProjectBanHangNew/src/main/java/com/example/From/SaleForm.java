package com.example.From;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SaleForm {

	private Integer quantity;

	private BigDecimal sale_price;

	private Date sale_date;

	private Integer productVersion;

	private Integer orderitem;
}
