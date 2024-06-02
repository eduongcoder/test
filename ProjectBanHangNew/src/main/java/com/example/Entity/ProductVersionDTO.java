package com.example.Entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductVersionDTO {
	private Integer productVersion_id;
	
    private BigDecimal price;

    private Integer quantity_in_stock;

    private Boolean isDelete;
	
	private Date dateDelete;
	
	private Date created_at;
	
	private Date updated_at;
	
	private Integer product;
	
	private List<VariantDTO> variants;

	private List<PurchaseOderItemsDTO> purchaseOderItems;
	
	private List<InventoriesDTO> inventories;

	private List<OrderItemDTO> orderItems;
	
	private List<SalesDTO> sales;
}
