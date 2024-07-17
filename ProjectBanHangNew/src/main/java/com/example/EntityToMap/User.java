package com.example.EntityToMap;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
	  private int id;
	    private int accountId;
	    private String city;
	    private String state;
	    private String country;
	    private String title;
	    private String phoneNumber;
	    private String createdAt;
	    private String updatedAt;
}
