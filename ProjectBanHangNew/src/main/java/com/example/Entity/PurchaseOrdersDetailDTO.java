package com.example.Entity;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseOrdersDetailDTO {
	private Integer purchase_orders_id;
	
	private LocalDateTime order_date;
	
    private Integer total_amount;
    
	private String status;
	
	private List<PurchaseOderItemsDetailDTO> purchaseorderitem;
	
	private List<HistoryPuchaseOrderDTO> historyPuchaseOrders;
}
