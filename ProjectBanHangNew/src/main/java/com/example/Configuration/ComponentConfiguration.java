package com.example.Configuration;

import java.math.BigDecimal;
import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.Entity.Category;
import com.example.Entity.CategoryDTO;
import com.example.Entity.Images;
import com.example.Entity.ImagesDTO;
import com.example.Entity.ImagesDTOOnlyID;
import com.example.Entity.Inventories;
import com.example.Entity.InventoriesDTO;
import com.example.Entity.OrderItem;
import com.example.Entity.OrderItemDTO;
import com.example.Entity.OrderItemDTOShopCart;
import com.example.Entity.OrderItemDTOVariant;
import com.example.Entity.Orders;
import com.example.Entity.OrdersDTOShopCart;
import com.example.Entity.PersonFix;
import com.example.Entity.PersonFixDTO;
import com.example.Entity.Product;
import com.example.Entity.ProductDTO;
import com.example.Entity.ProductShowDTO;
import com.example.Entity.ProductVersion;
import com.example.Entity.ProductVersionDTO;
import com.example.Entity.ProductVersionShowDTO;
import com.example.Entity.PurchaseOderItems;
import com.example.Entity.PurchaseOderItemsDTO;
import com.example.Entity.PurchaseOderItemsDetailDTO;
import com.example.Entity.Sales;
import com.example.Entity.SalesDTO;
import com.example.Entity.Variant;
import com.example.Entity.VariantDTO;
import com.example.Entity.VariantNoAccountDTO;
import com.example.Enum.SizeEnum;
import com.example.From.AccountForm;
import com.example.From.OrderitemForm;
import com.example.From.OrdersForm;
import com.example.From.PersonFixForm;
import com.example.From.ProductVersionForm;
import com.example.Repository.IProductRepository;
import com.example.Service.IOrderItemService;
import com.example.Service.IProductService;
import com.example.Service.IPurchaseOderItemsService;
import com.example.Service.ISaleService;
import com.example.Service.ImageHandelService;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@Configuration
public class ComponentConfiguration {

	@Autowired
	private ImageHandelService service;

	@Autowired
	private ISaleService saleService;

	@Autowired
	private IOrderItemService orderItemService;

	@Autowired
	private IPurchaseOderItemsService purchaseOderItemsService;
	

	
	@Bean
	public ModelMapper initModelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		Converter<Images, String> imageConverter = context -> {
			int imageID = context.getSource().getImages_id();
			return service.getImageBase64String(imageID);
		};

//		Converter<ProductVersion, Integer> priceConverter = context -> {
//			int saleID = context.getSource().getProductVersion_id();
//			return saleService.getSalePrice(saleID);
//		};
		Converter<OrderItem, SizeEnum> sizeConverter = context -> {
			int idvariant = context.getSource().getTypeOfVariant();
			return orderItemService.getSizeEnum(idvariant);
		};

		Converter<OrderItem, String> colorConverter = context -> {
			int idvariant = context.getSource().getTypeOfVariant();
			return orderItemService.getColor(idvariant);
		};
		
		Converter<PurchaseOderItems, SizeEnum> sizePurchaseConverter = context -> {
			int idvariant = context.getSource().getVariant();
			return purchaseOderItemsService.getSizeVariant(idvariant);
		};

		Converter<PurchaseOderItems, String> colorPurchaseConverter = context -> {
			int idvariant = context.getSource().getVariant();
			return purchaseOderItemsService.getColorVariant(idvariant);
		};
		
		Converter<PurchaseOderItems, List<ImagesDTOOnlyID>> imagesPurchaseConverter = context -> {
			int idvariant = context.getSource().getVariant();
			return purchaseOderItemsService.getImages(idvariant);
		};
		
		
		
		modelMapper.addMappings(new PropertyMap<Images, ImagesDTO>() {
			@Override
			protected void configure() {
				using(imageConverter).map(source, destination.getImage_urlString());
			}
		});
		
		modelMapper.addMappings(new PropertyMap<PurchaseOderItems, PurchaseOderItemsDetailDTO>() {
			@Override
			protected void configure() {
				using(sizePurchaseConverter).map(source, destination.getSizeEnum());
				using(colorPurchaseConverter).map(source, destination.getColor());
				using(imagesPurchaseConverter).map(source, destination.getUrlImage());
			}
		});
		modelMapper.addMappings(new PropertyMap<OrderItem, OrderItemDTOVariant>() {
			@Override
			protected void configure() {
				using(sizeConverter).map(source, destination.getSize());
				using(colorConverter).map(source, destination.getColor());
			}
		});
		modelMapper.addMappings(new PropertyMap<ProductVersion, ProductVersionShowDTO>() {
			@Override
			protected void configure() {
				map().setProduct(source.getProduct().getProduct_id());
//				using(priceConverter).map(source, destination.getPriceSale());
			}
		});
		modelMapper.addMappings(new PropertyMap<ProductVersion, ProductVersionDTO>() {
			@Override
			protected void configure() {
				map().setProduct(source.getProduct().getProduct_id());
			}
		});
		modelMapper.addMappings(new PropertyMap<Product, ProductShowDTO>() {
			@Override
			protected void configure() {
				map().setType(source.getTypeofproduct_id().getTypeofproduct());
				map().setGender(source.getTypeofproduct_id().getTypeofproductgender());
				
//				map().setType(source.getTypeofproduct().getTypeofproduct());
//				map().setGender(source.getTypeofproduct().getTypeofproductgender());

			}
		});

		modelMapper.addMappings(new PropertyMap<OrderItem, OrderItemDTO>() {
			@Override
			protected void configure() {
				map().setProductVersion(source.getProductVersion().getProductVersion_id());
				map().setVariants(null);
			}
		});

		modelMapper.addMappings(new PropertyMap<Sales, SalesDTO>() {
			@Override
			protected void configure() {
				map().setProductVersion(source.getProductVersion().getProductVersion_id());
			}
		});
		modelMapper.addMappings(new PropertyMap<Variant, VariantDTO>() {
			@Override
			protected void configure() {
				map().setColor(source.getColor().getColor_name());
				;
				map().setSize(source.getSize().getSize_name());
			}
		});
		modelMapper.addMappings(new PropertyMap<Variant, VariantNoAccountDTO>() {
			@Override
			protected void configure() {
				map().setProductversionName(source.getProductversion().getVersion_name());
//				map().setPrice(source.getProductversion().getPrice());
			}
		});
		modelMapper.addMappings(new PropertyMap<Inventories, InventoriesDTO>() {
			@Override
			protected void configure() {
				map().setProductVersion(source.getProductVersion().getProductVersion_id());
			}
		});
		modelMapper.addMappings(new PropertyMap<PurchaseOderItems, PurchaseOderItemsDTO>() {
			@Override
			protected void configure() {
				map().setProductVersion(source.getProductVersion().getProductVersion_id());
				map().setPurchaseOrder(source.getPurchaseOrder().getPurchase_orders_id());
			}
		});
		modelMapper.addMappings(new PropertyMap<OrdersForm, Orders>() {
			@Override
			protected void configure() {
				map().setAccount(source.getAccount());
			}
		});
		modelMapper.addMappings(new PropertyMap<Orders, OrdersDTOShopCart>() {
			@Override
			protected void configure() {
				map().setAccount(source.getAccount().getAccount_id());
			}
		});

		modelMapper.addMappings(new PropertyMap<OrderItem, OrderItemDTOShopCart>() {
			@Override
			protected void configure() {
				map().setProductVersion(source.getProductVersion().getProductVersion_id());
			}
		});
		modelMapper.addMappings(new PropertyMap<PersonFix, PersonFixDTO>() {
			@Override
			protected void configure() {
				map().setProduct_version_id(source.getProduct_version_id().getProductVersion_id());
			}
		});
		modelMapper.addMappings(new PropertyMap<Category, CategoryDTO>() {
			@Override
			protected void configure() {
				map().setCatetoryProduct(source.getCatetoryProduct().getProduct_id());
				map().setCatetoryColor(source.getCatetoryColor().getColor_id());
				map().setColor(source.getCatetoryColor().getColor_name());
				map().setSizeEnum(source.getCatetorySize().getSize_name());
			}
		});
		modelMapper.addMappings(new PropertyMap<PurchaseOderItems, PurchaseOderItemsDetailDTO>() {
			@Override
			protected void configure() {
				map().setProductVersion(source.getProductVersion().getProductVersion_id());
				map().setVersion_name(source.getProductVersion().getVersion_name());
				map().setNameProduct(source.getProductVersion().getProduct().getName());
				map().setGender(source.getProductVersion().getProduct().getTypeofproduct_id().getTypeofproductgender());
				map().setTypeOfProductEnum(source.getProductVersion().getProduct().getTypeofproduct_id().getTypeofproduct());
				map().setProductID(source.getProductVersion().getProduct().getProduct_id());

			}
		});
		
		
		return modelMapper;
	}
	
}
