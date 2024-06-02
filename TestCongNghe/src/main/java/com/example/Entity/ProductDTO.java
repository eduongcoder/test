package com.example.Entity;

import java.util.List;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class ProductDTO  {
	@NonNull
	private Integer productid;
	@NonNull
	private String productname;
	@NonNull
	private Integer productprice;
	@NonNull
	private String productmaterial;
	@NonNull
	private String productquantity;
	@NonNull
	private List<ImageProductDTO> image;
	@NonNull
	private List<SizeDTO> size;
}
