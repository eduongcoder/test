package com.example.Entity;


import java.time.LocalDateTime;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HistoryCategoryDTO {
	private int history_category_id;
	
	private int old_price;
	
	private int new_price;

	private LocalDateTime date_update;

	private Integer category;
}
