package com.example.Configuration;

import java.math.BigDecimal;
import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.Entity.Account;
import com.example.Entity.AccountDTO;
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
import com.example.Entity.RolePermission;
import com.example.Entity.RolePermissionDTO;
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
import com.example.Service.IAccountService;
import com.example.Service.IOrderItemService;
import com.example.Service.IProductService;
import com.example.Service.IPurchaseOderItemsService;
import com.example.Service.ISaleService;
import com.example.Service.ImageHandelService;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import net.bytebuddy.asm.MemberSubstitution.Source;

@Configuration
public class ComponentConfiguration {

	@Autowired
	private ImageHandelService service;

	@Autowired
	private ISaleService saleService;

	@Autowired
	private IOrderItemService orderItemService;
	
	@Autowired
	private IAccountService accountService;

	@Autowired
	private IPurchaseOderItemsService purchaseOderItemsService;
	

	
	@Bean
	public ModelMapper initModelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		Converter<Images, String> imageConverter = context -> {
			int imageID = context.getSource().getImages_id();
			return service.getImageBase64String(imageID);
		};
		
		Converter<Account, String> avatarConverter=context -> {
			if (context.getSource().getAvatar()!=null) {
				byte[] imageID = context.getSource().getAvatar();
				return service.getImageBase64String(imageID);
			}
			return null;
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
		
		Converter<Account, List<RolePermissionDTO>> roleAccountConverter = context -> {
			int idAccount = context.getSource().getAccount_id();
			return accountService.getRolePermissionDTOs(idAccount);
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
			int idvariant = context.getSource().getProductVersion().getProduct().getProduct_id();
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
		
		modelMapper.addMappings(new PropertyMap<RolePermission, RolePermissionDTO>() {
			@Override
			protected void configure() {
				map().setPermission(source.getPermission().getPermission_name());
				map().setRole(source.getRole().getRole());
			}
		});
		
		modelMapper.addMappings(new PropertyMap<Product, ProductShowDTO>() {
			@Override
			protected void configure() {
				map().setType(source.getTypeOfProductNew().getTypeofproduct());
				map().setGender(source.getTypeOfProductGender().getTypeOfProductGender());
				
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

//		modelMapper.addMappings(new PropertyMap<Sales, SalesDTO>() {
//			@Override
//			protected void configure() {
//				map().setProductVersion(source.getProductVersion().getProductVersion_id());
//			}
//		});
		modelMapper.addMappings(new PropertyMap<Images, ImagesDTO>() {
			@Override
			protected void configure() {
				map().setProductid(source.getProductid().getProduct_id());
			
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

		modelMapper.addMappings(new PropertyMap<Account, AccountDTO>() {
			@Override
			protected void configure() {
				map().setRoleID(source.getRoleID().getRole_id());
				using(avatarConverter).map(source,destination.getAvatarString());
				using(roleAccountConverter).map(source,destination.getRolePermission());
				
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
				map().setProduct_id(source.getProduct_id().getProduct_id());
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
				map().setTypeOfProductEnum(source.getProductVersion().getProduct().getTypeOfProductNew().getTypeofproduct());
				map().setProductID(source.getProductVersion().getProduct().getProduct_id());
				map().setGender(source.getProductVersion().getProduct().getTypeOfProductGender().getTypeOfProductGender());
				map().setQuantity_real(source.getQuantity_real());
			}
		});
		
		
		return modelMapper;
	}
	
}
