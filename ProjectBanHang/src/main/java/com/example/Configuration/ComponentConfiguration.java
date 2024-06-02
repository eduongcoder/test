package com.example.Configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.Entity.Product;
import com.example.Entity.ProductDTO;
import com.example.Entity.Variant;
import com.example.Entity.VariantDTO;


@Configuration
public class ComponentConfiguration {
	@Bean
	public ModelMapper initModelMapper() {
		ModelMapper modelMapper=new ModelMapper();
		
		modelMapper.addMappings(new PropertyMap<Product, ProductDTO>() {
			@Override
			protected void configure() {
//				map().setColor(source.getColorid().getColor());
//				map().setSizee(source.getSizeeid().getProductsize());
				map().setTypeofproduct(source.getTypeofproductid().getTypeofproduct());
				map().setGender(source.getTypeofproductid().getTypeofproductgender());

			}
		});
		modelMapper.addMappings(new PropertyMap<Variant, VariantDTO>() {
			@Override
			protected void configure() {
//				map().setColor(source.getColorid().getColor());
//				map().setSizee(source.getSizeeid().getProductsize());
				map().setSize(source.getStoreid().getProductsize());
				map().setColor(source.getStoreid().getColor());
				map().setQuantity(source.getStoreid().getProductquantity());
				map().setPriceroot(source.getStoreid().getPriceroot());
			}
		});
		
		return modelMapper;
	}
	 
}
