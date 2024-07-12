package com.example.Configuration;

import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.Entity.Account;
import com.example.Entity.AccountDTO;
import com.example.Entity.Category;
import com.example.Entity.CategoryDTO;
import com.example.Entity.Discount;
import com.example.Entity.DiscountDTO;
import com.example.Entity.DiscountOnlyDTO;
import com.example.Entity.HistoryLogin;
import com.example.Entity.HistoryLoginDTO;
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
import com.example.Entity.ProductShowDTO;
import com.example.Entity.ProductShowDTOVersion2;
import com.example.Entity.ProductShowDTOVersion3;
import com.example.Entity.ProductVersion;
import com.example.Entity.ProductVersionDTO;
import com.example.Entity.ProductVersionDTOOnlyStock;
import com.example.Entity.ProductVersionShowDTO;
import com.example.Entity.PurchaseOderItems;
import com.example.Entity.PurchaseOderItemsDTO;
import com.example.Entity.PurchaseOderItemsDetailDTO;
import com.example.Entity.RolePermission;
import com.example.Entity.RolePermissionDTO;
import com.example.Entity.SaleDiscount;
import com.example.Entity.SaleDiscountDTO;

import com.example.Entity.Variant;
import com.example.Entity.VariantDTO;
import com.example.Entity.VariantNoAccountDTO;
import com.example.Enum.SizeEnum;

import com.example.From.OrdersForm;

import com.example.Service.IAccountService;
import com.example.Service.ICategoryService;
import com.example.Service.IDiscountService;
import com.example.Service.IOrderItemService;
import com.example.Service.IProductVersionService;
import com.example.Service.IPurchaseOderItemsService;
import com.example.Service.ISaleDiscountService;
import com.example.Service.ISaleService;
import com.example.Service.ImageHandelService;


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
	private IProductVersionService productVersionService;

	@Autowired
	private IPurchaseOderItemsService purchaseOderItemsService;

	@Autowired
	private ISaleDiscountService saleDiscountService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private IDiscountService discountService;
	@Bean
	public ModelMapper initModelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		Converter<Images, String> imageConverter = context -> {
			int imageID = context.getSource().getImages_id();
			return service.getImageBase64String(imageID);
		};
		Converter<Discount, List<ProductShowDTOVersion3>> listProShowV3 = context -> {
			int idDiscount = context.getSource().getDiscount_id();
			return discountService.getProductShowDTOVersion3(idDiscount);
		};
		Converter<Account, String> avatarConverter = context -> {
			if (context.getSource().getAvatar() != null) {
				byte[] imageID = context.getSource().getAvatar();
				return service.getImageBase64String(imageID);
			}
			return null;
		};

		Converter<Variant, Integer> priceConverter = context -> {
			int saleID = context.getSource().getVariants_id();
			return saleService.getSalePrice(saleID);
		};
		
		Converter<Category, Integer> priceSaleConverter = context -> {
			int categoryID = context.getSource().getCategory_id();
			return categoryService.getSalePrice(categoryID);
		};

		Converter<ProductVersion, List<VariantDTO>> fillerStockVariant = context -> {
			int productVerionId = context.getSource().getProductVersion_id();
			return productVersionService.fillerStockVariant(productVerionId);
		};

//		Converter<OrderItem, SizeEnum> sizeConverter = context -> {
//			int idvariant = context.getSource().getTypeOfVariant();
//			return orderItemService.getSizeEnum(idvariant);
//		};
//
//		Converter<OrderItem, String> colorConverter = context -> {
//			int idvariant = context.getSource().getTypeOfVariant();
//			return orderItemService.getColor(idvariant);
//		};

		Converter<SaleDiscount, Integer> saleDiscountConverter = context -> {
			int idSale = context.getSource().getSales().getId();
			int idDiscount=context.getSource().getDiscount().getDiscount_id();
			return saleDiscountService.getTotalRevenue(idSale,idDiscount);
		};
		
		Converter<Account, List<RolePermissionDTO>> roleAccountConverter = context -> {
			int idAccount = context.getSource().getAccount_id();
			return accountService.getRolePermissionDTOs(idAccount);
		};

		Converter<PurchaseOderItems, SizeEnum> sizePurchaseConverter = context -> {
			int idvariant = context.getSource().getVariant();
			return purchaseOderItemsService.getSizeVariant(idvariant);
		};

//		Converter<SaleDiscount, ProductShowDTOVersion3> productshowConverter = context -> {
//			int idsale = context.getSource().getSales().getId();
//			return saleDiscountService.getProductShowDTOVersion3(idsale);
//		};
		
		Converter<SaleDiscount, DiscountOnlyDTO> getDiscountOnlyConverter = context -> {
		int idDiscount = context.getSource().getDiscount().getDiscount_id();
		return saleDiscountService.getDiscountOnlyDTO(idDiscount);
	};
		
		Converter<PurchaseOderItems, String> colorPurchaseConverter = context -> {
			int idvariant = context.getSource().getVariant();
			return purchaseOderItemsService.getColorVariant(idvariant);
		};

		Converter<PurchaseOderItems, List<ImagesDTOOnlyID>> imagesPurchaseConverter = context -> {
			int idvariant = context.getSource().getProductVersion().getProduct().getProduct_id();
			return purchaseOderItemsService.getImages(idvariant);
		};

		modelMapper.addMappings(new PropertyMap<Variant, VariantDTO>() {
			@Override
			protected void configure() {
				map().setColor(source.getColor().getColor_name());
				using(priceConverter).map(source, destination.getPrice());
				map().setSize(source.getSize().getSize_name());
			}
		});

		modelMapper.addMappings(new PropertyMap<Images, ImagesDTO>() {
			@Override
			protected void configure() {
				using(imageConverter).map(source, destination.getImage_urlString());
			}
		});
		
//		modelMapper.addMappings(new PropertyMap<Sales, SalesDTO>() {
//			@Override
//			protected void configure() {
//				map().setSale_base_price( source.getSale_base_price());
//				map().setSale_price(source.getSale_price());
//			}
//		});
		
		modelMapper.addMappings(new PropertyMap<SaleDiscount, SaleDiscountDTO>() {
			@Override
			protected void configure() {
//				using(productshowConverter).map(source, destination.getProduct());
				map().setVariant(source.getSales().getVariant_id().getVariants_id());
				map().setProductID(source.getSales().getVariant_id().getProductversion().getProduct().getProduct_id());
				map().setPriceBase(source.getSales().getSale_price());
				map().setPriceSale(source.getSales().getSale_base_price()); 
				map().setColor(source.getSales().getVariant_id().getColor().getColor_name());
				map().setSize(source.getSales().getVariant_id().getSize().getSize_name()); 


				using(saleDiscountConverter).map(source,destination.getTotalMoney());
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
				map().setSize(source.getSize().getSize_name());
				map().setSizeID(source.getSize().getSize_id());
				map().setColorID(source.getColor().getColor_id());
				map().setColor(source.getColor().getColor_name());
				map().setProductID(source.getProduct().getProduct_id());
				map().setPrice_base(source.getBase_price());
				map().setCreatedAt(source.getCreatedAt());
				map().setUpdatedAt(source.getUpdatedAt());
			}
		});

		modelMapper.addMappings(new PropertyMap<ProductVersion, ProductVersionShowDTO>() {
			@Override
			protected void configure() {
				map().setProduct(source.getProduct().getProduct_id());
			}
		});
		
//		modelMapper.addMappings(new PropertyMap<Product, ProductShowDTOVersion3>() {
//			@Override
//			protected void configure() {
//				map().setType(source.getTypeOfProductNew().getTypeofproduct());
//			}
//		});

		modelMapper.addMappings(new PropertyMap<ProductVersion, ProductVersionDTOOnlyStock>() {
			@Override
			protected void configure() {
				using(fillerStockVariant).map(source, destination.getVariants());
				map().setProduct(source.getProduct().getProduct_id());
			}
		});

		modelMapper.addMappings(new PropertyMap<Discount, DiscountDTO>() {
			@Override
			protected void configure() {
				using(listProShowV3).map(source, destination.getProductShow());
				
			}
		});
		

		
		modelMapper.addMappings(new PropertyMap<ProductVersion, ProductVersionDTO>() {
			@Override
			protected void configure() {
				map().setProduct(source.getProduct().getProduct_id());
			}
		});

		
		
		modelMapper.addMappings(new PropertyMap<HistoryLogin, HistoryLoginDTO>() {
			@Override
			protected void configure() {
				map().setId_account(source.getId_account().getAccount_id());;
			}
		});
		
		modelMapper.addMappings(new PropertyMap<RolePermission, RolePermissionDTO>() {
			@Override
			protected void configure() {
				map().setPermission(source.getPermission().getPermission_name());
				map().setRole(source.getRole().getRole());
			}
		});

		modelMapper.addMappings(new PropertyMap<SaleDiscount, SaleDiscountDTO>() {
			@Override
			protected void configure() {
				map().setDiscount(source.getDiscount().getDiscount_id());
				map().setSales(source.getSales().getId());
				using(getDiscountOnlyConverter).map(source,destination.getDiscountshow());
			}
		});
		
		modelMapper.addMappings(new PropertyMap<Product, ProductShowDTO>() {
			@Override
			protected void configure() {
				map().setType(source.getTypeOfProductNew().getTypeofproduct());
				map().setGender(source.getTypeOfProductGender().getTypeOfProductGender());

			}
		});
		
		modelMapper.addMappings(new PropertyMap<Product, ProductShowDTOVersion3>() {
			@Override
			protected void configure() {
				map().setType(source.getTypeOfProductNew().getTypeofproduct());
				map().setGender(source.getTypeOfProductGender().getTypeOfProductGender());
			}
		});
		
		modelMapper.addMappings(new PropertyMap<Product, ProductShowDTOVersion2>() {
			@Override
			protected void configure() {
				map().setType(source.getTypeOfProductNew().getTypeofproduct());
				map().setGender(source.getTypeOfProductGender().getTypeOfProductGender());
			}
		});

		modelMapper.addMappings(new PropertyMap<OrderItem, OrderItemDTO>() {
			@Override
			protected void configure() {
//				map().setProductVersion(source.getProductVersion().getProductVersion_id());
//				map().setIdvariant(source.getTypeOfVariant());
			}
		});

		modelMapper.addMappings(new PropertyMap<Images, ImagesDTO>() {
			@Override
			protected void configure() {
				map().setProductid(source.getProductid().getProduct_id());

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
				map().setInventoryVariant(source.getInventoryVariant().getVariants_id());
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
				using(avatarConverter).map(source, destination.getAvatarString());
				using(roleAccountConverter).map(source, destination.getRolePermission());

			}
		});

		modelMapper.addMappings(new PropertyMap<OrderItem, OrderItemDTOShopCart>() {
			@Override
			protected void configure() {
//				map().setProductVersion(source.getProductVersion().getProductVersion_id());
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
				using(priceSaleConverter).map(source,destination.getPrice_sale());
			}
		});
		modelMapper.addMappings(new PropertyMap<PurchaseOderItems, PurchaseOderItemsDetailDTO>() {
			@Override
			protected void configure() {
				map().setProductVersion(source.getProductVersion().getProductVersion_id());
				map().setVersion_name(source.getProductVersion().getVersion_name());
				map().setNameProduct(source.getProductVersion().getProduct().getName());
				map().setTypeOfProductEnum(
						source.getProductVersion().getProduct().getTypeOfProductNew().getTypeofproduct());
				map().setProductID(source.getProductVersion().getProduct().getProduct_id());
				map().setGender(
						source.getProductVersion().getProduct().getTypeOfProductGender().getTypeOfProductGender());
				map().setQuantity_real(source.getQuantity_real());
			}
		});

		return modelMapper;
	}

}
