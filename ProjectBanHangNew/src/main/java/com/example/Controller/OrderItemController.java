package com.example.Controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.OrderItem;
import com.example.Entity.OrderItemDTO;
import com.example.Entity.ProductVersionDTO;
import com.example.Entity.ProductVersionShowDTO;
import com.example.Entity.Variant;
import com.example.Entity.VariantDTO;
import com.example.From.OrderitemForm;
import com.example.Service.IOrderItemService;
import com.example.Service.IProductVersionService;
import com.example.Service.IVariantService;

@RequestMapping("/api/ordersitem")
@RestController
public class OrderItemController {
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IOrderItemService service;
	
	@Autowired
	private IVariantService serviceVariant;
	
	@Autowired
	private IProductVersionService serviceProductVersion;
	
	@PutMapping(value = "/updatequantity/{id}")
	public boolean updateOrderItemQuantity(@PathVariable(name = "id")int id,@RequestParam int quantity) {
		return service.updateOrderItemQuantity(id, quantity);
	}
	@PutMapping(value = "/updateorderitem/{id}")
	public VariantDTO updatOrderItemDTO(@PathVariable(name = "id")int id,@RequestParam int idvariant) {
//		Variant variant=serviceVariant.getVariantByID(idvariant);
//		VariantDTO variantDTO=modelMapper.map(variant, VariantDTO.class);
//		
		OrderItem orderItem=service.findbyID(id);
//		OrderItemDTO orderItemDTO=modelMapper.map(orderItem, OrderItemDTO.class);
//		orderItemDTO.setVariants(variantDTO);
//		orderItemDTO.setIdvariant(idvariant);
		ProductVersionShowDTO productVersionShowDTO=modelMapper.map(serviceProductVersion.getProductVersionByID(orderItem.getProductVersion().getProductVersion_id()), ProductVersionShowDTO.class);
		for (VariantDTO variantdto : productVersionShowDTO.getVariants()) {
			if (variantdto.getVariants_id()==idvariant) {
				return variantdto;
			}
		}
		return null;
	}
	@DeleteMapping(value = "/deleteorderitem")
	public boolean deleteOrderItem(@RequestParam int id) {
		return service.deleteOrderItem(id);
	}
	@DeleteMapping(value = "/deleteallorderitem")
	public boolean deleteAllOrderItem(@RequestParam int idOrder) {
		return service.deleteOrderItemAll(idOrder);
	}
}
