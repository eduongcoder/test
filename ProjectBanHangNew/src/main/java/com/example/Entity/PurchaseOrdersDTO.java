package com.example.Entity;


import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseOrdersDTO {
	private Integer purchase_orders_id;
	
	private LocalDateTime order_date;
	
	private LocalDateTime order_update_date;

	
    private Integer total_amount;
    
	private String status;
	
	private List<PurchaseOderItemsDTO> purchaseorderitem;
	
	private List<HistoryPuchaseOrderDTO> historyPuchaseOrders;

}
