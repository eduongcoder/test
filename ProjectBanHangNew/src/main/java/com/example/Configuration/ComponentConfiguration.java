package com.example.Configuration;




import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.Entity.Account;
import com.example.Entity.AccountDTO;
import com.example.Entity.Addresses;
import com.example.Entity.AddressesDTO;
import com.example.Entity.Images;
import com.example.Entity.ImagesDTO;
import com.example.Entity.Inventories;
import com.example.Entity.InventoriesDTO;
import com.example.Entity.OrderItem;
import com.example.Entity.OrderItemDTO;
import com.example.Entity.OrderItemDTOShopCart;
import com.example.Entity.OrderItemDTOVariant;
import com.example.Entity.Orders;
import com.example.Entity.OrdersDTO;
import com.example.Entity.OrdersDTOShopCart;
import com.example.Entity.Product;
import com.example.Entity.ProductDTO;
import com.example.Entity.ProductShowDTO;
import com.example.Entity.ProductVersion;
import com.example.Entity.ProductVersionDTO;
import com.example.Entity.ProductVersionShowDTO;
import com.example.Entity.PurchaseOderItems;
import com.example.Entity.PurchaseOderItemsDTO;
import com.example.Entity.Sales;
import com.example.Entity.SalesDTO;
import com.example.Entity.Variant;
import com.example.Entity.VariantDTO;
import com.example.Entity.VariantNoAccountDTO;
import com.example.From.AccountForm;
import com.example.From.OrderitemForm;
import com.example.Service.ImageHandelService;


@Configuration
public class ComponentConfiguration {

	@Autowired
	private ImageHandelService service;
	
	@Bean
	public ModelMapper initModelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		

		  Converter<Images, String> imageConverter = context -> {
	            int imageID = context.getSource().getImages_id();
	            return service.getImageBase64String(imageID);
	        };
	        modelMapper.addMappings(new PropertyMap<Images, ImagesDTO>() {
	            @Override
	            protected void configure() {
	                using(imageConverter).map(source, destination.getImage_urlString());
	               
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
				map().setType(source.getTypeofproduct().getTypeofproduct());
				map().setGender(source.getTypeofproduct().getTypeofproductgender());

			}
		});
		modelMapper.addMappings(new PropertyMap<ProductVersion, ProductVersionShowDTO>() {
			@Override
			protected void configure() {
				map().setProduct(source.getProduct().getProduct_id());

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
				map().setPrice(source.getProductversion().getPrice());
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
		modelMapper.addMappings(new PropertyMap<Orders, OrdersDTO>() {
			@Override
			protected void configure() {
//				map().setAccount(source.getAccount().getAccount_id());
			}
		});
		modelMapper.addMappings(new PropertyMap<Orders, OrdersDTOShopCart>() {
			@Override
			protected void configure() {
				map().setAccount(source.getAccount().getAccount_id());
			}
		});
		
		modelMapper.addMappings(new PropertyMap<OrderItem, OrderItemDTOVariant>() {
			@Override
			protected void configure() {
//				map().setProductVersion(source.getProductVersion().getProductVersion_id());

			}
		});
		modelMapper.addMappings(new PropertyMap<OrderItem, OrderItemDTOShopCart>() {
			@Override
			protected void configure() {
				map().setProductVersion(source.getProductVersion().getProductVersion_id());
			}
		});


//		modelMapper.addMappings(new PropertyMap<OrderitemForm,OrderItem >() {
//			@Override
//			protected void configure() {
//				map().setProductVersion();;
//			}
//		}); 
		

//		modelMapper.addMappings(new PropertyMap<Variant, VariantDTO>() {
//			@Override
//			protected void configure() {
//				map().setColor(source.getColorid().getColor());
//				map().setSizee(source.getSizeeid().getProductsize());
//				map().setSize(source.getStoreid().getProductsize());
//				map().setColor(source.getStoreid().getColor());
//				map().setQuantity(source.getStoreid().getProductquantity());
//				map().setPriceroot(source.getStoreid().getPriceroot());
//			}
//		});
//		
		return modelMapper;
	}

}
