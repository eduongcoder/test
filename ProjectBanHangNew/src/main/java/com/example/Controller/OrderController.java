package com.example.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.Entity.OrderItem;
import com.example.Entity.OrderItemDTOShopCart;
import com.example.Entity.OrderItemStatisticDTO;
import com.example.Entity.Orders;
import com.example.Entity.OrdersDTO;
import com.example.Entity.OrdersDTOShopCart;
import com.example.Entity.OrdersStatisticsDTO;
import com.example.From.OrderAddressForm;
import com.example.From.OrdersForm;
import com.example.Service.IAccountService;
import com.example.Service.IOrderItemService;
import com.example.Service.IOrderService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/api/orders")
@RestController
public class OrderController implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://26.229.166.254:5173", "http://localhost:5173","http://localhost:5174") // URL của ứng
																											// dụng
																											// React
				.allowedMethods("GET", "POST", "PUT", "DELETE").allowedHeaders("*").allowCredentials(true);
	}

	@Autowired
	private IOrderService service;

	@Autowired
	private IOrderItemService serviceOrderItem;

	@Autowired
	private IAccountService accountService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping(value  = "/createOrder/{accountid}")
	private int createOrder(@RequestBody OrdersForm form, @PathVariable(name = "accountid") int accountid) {
		form.setAccount(accountService.findAccountByID(accountid));
		return service.createOrderPending(form);
	}

	@PutMapping(value = "/updateOrderAddress/{id}")
	private OrdersDTO updateOrderAddress(@PathVariable(name = "id") int id, @RequestBody OrderAddressForm address) {

		return modelMapper.map(service.updateAdressOrder(id, address.getAddreString()), OrdersDTO.class);
	}

	@PutMapping(value = "/updateOrderTotalMoney/{id}")
	private OrdersDTO updateOrderTotalMoney(@PathVariable(name = "id") int id) {

//		return modelMapper.map(service.updateAdressOrder(id, address.getAddreString()), OrdersDTO.class);
		return null;
	}
	
	@PutMapping(value = "/updateStatusOrder/{id}")
	private OrdersDTO updateStatusOrder(@PathVariable(name = "id")int id, @RequestParam String status) {
		
		return modelMapper.map(service.updateStatusOrders(id,status), OrdersDTO.class);
	}
	
	@PutMapping(value = "/updateOrder/{id}")
	private OrdersDTO updateOrder(@PathVariable(name = "id")int id, @RequestBody OrdersForm form) {
		
		return modelMapper.map(service.updateOrder(id,form), OrdersDTO.class);
	}
	
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

//			dto.setOrderItems(dtos);
			dto.setIdvariant(idVariant);
			return dto;
		}
		return null;
	}

	@PutMapping(value = "/{id}")
	private OrdersDTO completeShopCart(@PathVariable(name = "id") int id) {

//		return modelMapper.map(service.updateOrder(id), OrdersDTO.class);
		return null;
	}

	@GetMapping("/countOrder")
	private Map<LocalDate, Integer> countOrder() {
		Map<LocalDate, Integer> myMap = new LinkedHashMap<LocalDate, Integer>();
		List<Orders> list = service.getallOrders();
		for (Orders orders : list) {
			LocalDate date = orders.getCreated_at().toLocalDate();
			int count = 1;
			for (Orders orders2 : list) {

				if (orders2.getOrders_id() == orders.getOrders_id()) {
					continue;
				} else if (date.equals(orders2.getCreated_at().toLocalDate())) {
					count++;
				}
			}
			myMap.put(date, count);
		}

		return myMap;
	}

	@GetMapping("/totalOrderMoney")
	private Map<LocalDate, Integer> totalOrderMoney() {
		return service.totalOrderMoney();
	}

	@GetMapping("/getrevenuebydate")
	public Map<String, List<OrdersStatisticsDTO>> getRevenue(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
		Map<String, List<OrdersStatisticsDTO>> myMap = new LinkedHashMap<>();
		List<Orders> list = service.getallOrders();
		for (Orders item : list) {
			
			if (item.getCreated_at().toLocalDate().isAfter(startDate) && item.getCreated_at().toLocalDate().isBefore(endDate)
					&& !item.getStatus().equals("Prepare")&& !item.getStatus().equals("Cancel")
					) { 
				OrdersStatisticsDTO dto=new OrdersStatisticsDTO();
				int idAccount=item.getAccount().getAccount_id();
				int idOrder=item.getOrders_id(),totalMoney=item.getTotal_amount();
				String date = item.getCreated_at().toLocalDate().toString();
				dto.setIdAccount(idAccount);
				dto.setIdOrder(idOrder);
				dto.setTotalMoney(totalMoney);
				
			
				myMap.computeIfAbsent(date, k -> new ArrayList<>()).add(dto);
			}
		
		}

		return myMap;

	}
	
}
