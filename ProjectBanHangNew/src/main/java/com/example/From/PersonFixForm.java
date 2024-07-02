package com.example.From;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonFixForm {
	private int person_fix_id;

	private String note;

	private Integer account_id;

	private Integer product_id;

}
