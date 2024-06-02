package com.example.From;

import io.micrometer.common.lang.NonNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientProductForm {
	@NonNull
	private Integer productID;
	@NonNull
	private Integer accountID;
	@NonNull
	private Integer typeofproductid;
	@NonNull
	private Integer sizeeid;
	@NonNull
	private Integer priceid;
	private String clientname;
	private String clientphonenumber;
	private String clientemail;
	private String clientcity;
	private String clientward;
	private String clientdistrict;
	private String clientstreet;
	private Integer productbuy;
}
