package com.example.Entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientProductId implements Serializable {
	private static final long serialVersionUID=1;

	private int variantID;
	private int accountID;
	
	
}
