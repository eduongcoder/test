package com.example.Entity;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InventoriesDTO {
	private Integer inventory_id;

	private Integer productVersion;

	private Integer change_amount;

	private String event_type;

	private Date event_date;

	private Integer orders;
}
