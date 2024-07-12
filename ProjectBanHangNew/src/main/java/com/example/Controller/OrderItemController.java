package com.example.Controller;


import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.OrderItem;
import com.example.Entity.OrderItemDTO;
import com.example.Entity.OrderItemDTOVariant;
import com.example.Entity.ProductVersion;
import com.example.Entity.ProductVersionShowDTO;
import com.example.Entity.SaleDiscount;
import com.example.Entity.Variant;
import com.example.Entity.VariantDTO;
import com.example.From.OrderitemForm;
import com.example.Service.IOrderItemService;
import com.example.Service.IOrderService;
import com.example.Service.IProductVersionService;
import com.example.Service.ISaleDiscountService;
import com.example.Service.ISaleService;
import com.example.Service.IVariantService;

@RequestMapping("/api/ordersitem")
@RestController
public class OrderItemController {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IOrderItemService service;

	@Autowired
	private IProductVersionService serviceProductVersion;

	@Autowired
	private ISaleService saleService;
	
	@Autowired
	private IVariantService variantService;
	
	@Autowired
	private ISaleDiscountService saleDiscountService;
	
	@PostMapping("/createorderitem")
	public OrderItemDTOVariant createOrderItem(@RequestBody OrderitemForm form) {
//		OrderItem orderItem= service.createOrderItem(form, form.getOrders(), form.getProductVersion());
		
//		Variant variant=variantService.getVariantByID(orderItem.getTypeOfVariant());
//		List<SaleDiscount> saleDiscounts=variant.getSales().getSaleDiscount();
//		if (!saleDiscounts.isEmpty() && saleDiscounts!=null) {
//			for (SaleDiscount saleDiscount : saleDiscounts) {
//				LocalDateTime starTime=saleDiscount.getDiscount().getDate_start(),endTime=saleDiscount.getDiscount().getDate_end();
//				LocalDateTime time=service.findbyID(orderItem.getOrder_items_id()).getCreatedAt();
//				
//				if (time.isAfter(starTime)&& time.isBefore(endTime)) {
//					saleDiscountService.updateSaleDiscount(saleDiscount.getSales().getId(), saleDiscount.getDiscount().getDiscount_id(), orderItem.getQuantity());
//
//				}
//			}
//		}else {
//			saleService.updateQuantitySales(orderItem.getTypeOfVariant(), orderItem.getQuantity());
//		}
		
//		return modelMapper.map(orderItem, OrderItemDTOVariant.class);
		return null;
	}
	
	@PostMapping("/createorderitemform")
	public OrderItemDTO createOrderItemForm(@RequestBody OrderitemForm form) {
//		ProductVersion productVersion=serviceProductVersion.getProductVersionByID(form.getProductVersion());
		OrderItem orderItem= modelMapper.map(form, OrderItem.class);
//		orderItem.setProductVersion(productVersion);
//		return orderItem;
		return modelMapper.map(orderItem, OrderItemDTO.class);
	}
	
	@PutMapping(value = "/updatequantity/{id}")
	public boolean updateOrderItemQuantity(@PathVariable(name = "id") int id, @RequestParam int quantity) {
		return service.updateOrderItemQuantity(id, quantity);
	}
	
	@PutMapping("/updateorderitemfull")
	public OrderItemDTOVariant updateOrderItemFull(@RequestBody OrderitemForm form) {
		return modelMapper.map(service.updateOrderItem(form), OrderItemDTOVariant.class) ;
	}

	@PutMapping(value = "/updateorderitem/{id}")
	public VariantDTO updatOrderItemDTO(@PathVariable(name = "id") int id, @RequestParam int idvariant) {

		OrderItem orderItem = service.findbyID(id);

//		ProductVersionShowDTO productVersionShowDTO = modelMapper.map(
//				serviceProductVersion.getProductVersionByID(orderItem.getProductVersion().getProductVersion_id()),
//				ProductVersionShowDTO.class);
//		for (VariantDTO variantdto : productVersionShowDTO.getVariants()) {
//			if (variantdto.getVariants_id() == idvariant) {
//				return variantdto;
//			}
//		}
		return null;
	}


	@DeleteMapping(value = "/deleteorderitem")
	public int deleteOrderItem(@RequestParam int id) {
		try {
			 service.deleteOrderItem(id);
			 return id;
		} catch (Exception e) {
			return -1;
		}
		
	}

	@DeleteMapping(value = "/deleteallorderitem")
	public boolean deleteAllOrderItem(@RequestParam int idOrder) {
		return service.deleteOrderItemAll(idOrder);
	}
}
