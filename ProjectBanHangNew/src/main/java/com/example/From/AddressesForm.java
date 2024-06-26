package com.example.From;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressesForm {
	private int id;

	private Integer account_id;

	private String city;

	private String state;

	private String country;

	private String title;

	private String phonenumer;

	private LocalDateTime created_at;

	private LocalDateTime updated_at;
}
