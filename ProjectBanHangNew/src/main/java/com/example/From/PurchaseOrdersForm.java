package com.example.From;



import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseOrdersForm {
	private int purchase_orders_id;

    private int total_amount;
    
	private String status;


}
