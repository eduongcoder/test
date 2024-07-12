package com.example.From;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressesForm {
	private Integer id;

	private Integer account_id;

	private String city;

	private String state;

	private String country;

	private String title;

	private String phonenumer;

}
