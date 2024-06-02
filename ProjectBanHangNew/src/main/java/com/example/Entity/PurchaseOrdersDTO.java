package com.example.Entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseOrdersDTO {
	private Integer purchase_orders_id;

	private Integer supplier_id;
	
	private Date order_date;
	
    private BigDecimal total_amount;
    
	private String status;
	
	private List<PurchaseOderItemsDTO> purchaseorderitem;
}
