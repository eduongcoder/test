package com.example.Controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.OrderItem;
import com.example.Entity.OrderItemDTO;
import com.example.Entity.OrderItemDTOShopCart;
import com.example.Entity.OrderItemDTOVariant;
import com.example.Entity.Orders;
import com.example.Entity.OrdersDTO;
import com.example.Entity.OrdersDTOShopCart;
import com.example.Entity.Variant;
import com.example.Entity.VariantDTO;
import com.example.From.OrderitemForm;
import com.example.Service.IOrderItemService;
import com.example.Service.IOrderService;
import com.example.Service.IVariantService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/api/orders")
@RestController
public class OrderController {

	@Autowired
	private IOrderService service;

	@Autowired
	private IOrderItemService serviceOrderItem;

	@Autowired
	private IVariantService serviceVariantService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	private List<OrdersDTO> getAllOrder() {
		List<Orders> list = service.getallOrders();
		List<OrdersDTO> dtos = modelMapper.map(list, new TypeToken<List<OrdersDTO>>() {
		}.getType());
		return dtos;
	}

	@GetMapping("/orderaccount")
	private OrdersDTOShopCart getAllOrderIDAccount(@RequestParam int idAccount, @RequestParam int idVariant) {
		Orders orders = service.getOrderPrepare(idAccount);
		if (orders != null) {
			orders.setOrderItems(serviceOrderItem.findbyIDOrdersItem(orders.getOrders_id()));
			OrdersDTOShopCart dto = modelMapper.map(orders, OrdersDTOShopCart.class);

			List<OrderItem> list = serviceOrderItem.findbyIDOrdersItem(dto.getOrders_id());
			List<OrderItemDTOShopCart> dtos = modelMapper.map(list, new TypeToken<List<OrderItemDTOShopCart>>() {
			}.getType());

//			List<Variant> variant = serviceVariantService.getAllVariants();
//			List<VariantDTO> variantDTOs = modelMapper.map(variant, new TypeToken<List<VariantDTO>>() {
//			}.getType());

			dto.setOrderItems(dtos);
			dto.setIdvariant(idVariant);
			return dto;
		}
		return null;
	}



}