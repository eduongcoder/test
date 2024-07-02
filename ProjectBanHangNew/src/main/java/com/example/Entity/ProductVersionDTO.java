package com.example.Entity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductVersionDTO {
	private Integer productVersion_id;

	private String version_name;

	private Integer quantity_in_stock;

	private Boolean isDelete;

	private Date dateDelete;

	private LocalDateTime created_at;

	private LocalDateTime updated_at;

	private Integer product;


	private List<VariantDTO> variants;

	private List<PurchaseOderItemsDTO> purchaseOderItems;

	private List<OrderItemDTO> orderItems;


}
