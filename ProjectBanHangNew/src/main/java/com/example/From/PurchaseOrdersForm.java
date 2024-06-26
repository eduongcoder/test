package com.example.From;

import java.util.List;

import com.example.Entity.PurchaseOderItemsDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseOrdersForm {
	private int purchase_orders_id;

    private int total_amount;
    
	private String status;

	private List<PurchaseOderItemsDTO> purchaseorderitem;

}
