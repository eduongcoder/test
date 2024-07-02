package com.example.Entity;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HistoryPuchaseOrderDTO {
	private Integer history_puchase_order_id;

	private LocalDateTime date_fix;

	private String note;

	private Integer account_id;

	private Integer puchase_order_id;
}
