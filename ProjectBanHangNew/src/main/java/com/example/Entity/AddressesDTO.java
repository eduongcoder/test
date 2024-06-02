package com.example.Entity;

import java.sql.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressesDTO {

	private int id;

	private Integer account;

	private String city;

	private String state;

	private String country;

	private Date created_at;

	private Date updated_at;
}
