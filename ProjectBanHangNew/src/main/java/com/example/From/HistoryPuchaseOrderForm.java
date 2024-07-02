package com.example.From;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HistoryPuchaseOrderForm {
	private Integer history_puchase_order_id;

	private String note;

	private Integer account_id;

	private Integer puchase_order_id;
}
