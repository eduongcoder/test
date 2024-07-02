package com.example.From;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import com.example.Entity.ImagesDTO;
import com.example.Entity.InventoriesDTO;
import com.example.Entity.OrderItemDTO;
import com.example.Entity.ProductDTO;
import com.example.Entity.ProductVersionDTO;
import com.example.Entity.PurchaseOderItemsDTO;
import com.example.Entity.SalesDTO;
import com.example.Entity.VariantDTO;
import com.example.Enum.SizeEnum;
import com.example.Enum.TypeOfProductEnum;
import com.example.Enum.TypeOfProductGenderEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TypeProductVersionSizeColorVariantForm {
	// Type product
	private int typeofproduct_id;

	private TypeOfProductEnum typeofproductTYPE;

	private TypeOfProductGenderEnum typeofproductgender;

	private List<ProductDTO> Product;

	// size
	private int size_id;

	private SizeEnum size_name;

	private List<VariantDTO> variants;

	// product version
	private int productVersion_id;

	private String version_name;

	private BigDecimal price;

	private Integer quantity_in_stock;

	private Boolean isDelete;

	private Date dateDelete;

	private List<VariantDTO> variantsVersion;

	private List<PurchaseOderItemsDTO> purchaseOderItems;

	private List<InventoriesDTO> inventories;

	private List<OrderItemDTO> orderItems;

	private List<SalesDTO> sales;

	// color
	private int color_id;

	private String color_name;

	private List<VariantDTO> variantsColor;

	// product
	private int product_id;

	private String name;

	private String description;

	private String state;

	private String materialProduct;

	private List<ProductVersionDTO> productVersion;

	// variant
	private int variants_id;

	private int quantity_in_stockVariant;

	private List<ImagesDTO> images;
}
