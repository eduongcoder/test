package com.example.Entity;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonFixDTO {
	private Integer person_fix_id;

	private LocalDateTime date_fix;

	private String note;
	
	private Integer account_id;

	private Integer product_version_id;
}
