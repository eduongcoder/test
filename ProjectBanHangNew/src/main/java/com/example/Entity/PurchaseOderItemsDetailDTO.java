package com.example.Entity;

import java.util.List;

import com.example.Enum.Gender;
import com.example.Enum.SizeEnum;
import com.example.Enum.TypeOfProductEnum;
import com.example.Enum.TypeOfProductGender;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PurchaseOderItemsDetailDTO {
	private Integer purchase_order_items_id;

	private Integer purchase_price;

	private Integer quantity;

	private Integer purchaseOrder;

	private Integer variant;

	private Integer productVersion;
	
	private String version_name;
	
	private String color;
	
	private SizeEnum sizeEnum;
	
	private List<ImagesDTO> urlImage;
	
	private String nameProduct;
	
	private TypeOfProductGender gender;
	
	private TypeOfProductEnum typeOfProductEnum;
	
}
