package com.example.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Inventories;
import com.example.Entity.OrderItem;
import com.example.Entity.OrderItemDTO;
import com.example.Entity.OrderItemDTOVariant;
import com.example.Entity.Orders;
import com.example.From.InventoriesForm;
import com.example.From.OrdersForm;
import com.example.Repository.IOrderRepository;

@Service
public class OrderService implements IOrderService {

	@Autowired
	private IOrderRepository service;

	@Autowired
	private IVariantService variantService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IInventoryService inventoryService;
	
	@Override
	public List<Orders> getallOrders() {
		return service.findAll();
	}

	@Override
	public Orders createOrder(OrdersForm form) {
		try {
//			form.setAccount(accountService.findAccountByID(form.getAccountId()));
			Orders orders = modelMapper.map(form, Orders.class);
			
			return service.save(orders);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public void deleteOrder(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Orders updateOrder(int id) {
		Orders orders = getOrderByID(id);
		orders.setStatus("Pending");

		try {
			
			return service.save(orders);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public Orders getOrderPrepare(int id) {
		List<Orders> list = getallOrders();

		for (Orders orders : list) {
			if (orders.getAccount().getAccount_id() == id && orders.getStatus().equals("Prepare")) {
				return orders;
			}
		}
		return null;
	}

	@Override
	public Orders getOrderByID(int id) {

		return service.findById(id).get();
	}

	@Override
	public int createOrderPending(OrdersForm form) {
		try {
			form.setStatus("Pending");
			Orders orders=modelMapper.map(form, Orders.class);
			
			
			return service.save(orders).getOrders_id();
		} catch (Exception e) {
			return -1;
		}
		
	}

	@Override
	public boolean updateAdressOrder(int id,String address) {
		try {
			Orders orders=getOrderByID(id);
			
			orders.setAddressorder(address);
			service.save(orders);
			return true;
		} catch (Exception e) {
			return false;
		}
		
		
		
	}

	@Override
	public Map<LocalDate, Integer> totalOrderMoney() {
		Map<LocalDate, Integer> myMap = new LinkedHashMap<LocalDate, Integer>();
		List<Orders> list = getallOrders();
		for (Orders orders : list) {
			LocalDate date = orders.getCreated_at().toLocalDate();
			int sum =0;
			for (Orders orders2 : list) {
				
				if (orders2.getOrders_id() == orders.getOrders_id()) {
					continue;
				} else if (date.equals( orders2.getCreated_at().toLocalDate()) && !orders2.getStatus().equals("Prepare") && !orders2.getStatus().equals("Cancel")) {
					sum+=orders2.getTotal_amount() ;
				}
			}
			myMap.put(date, sum);
		}

		return myMap;
	}

	@Override
	public Orders updateStatusOrders(int id, String status) {
		Orders orders=getOrderByID(id);
		if (status.equals("Shipping")) {
			orders.setShipping_at(LocalDateTime.now());
		}else if (status.equals("Completed")) {
			orders.setComplete_at(LocalDateTime.now());
		}else if (status.equals("Cancel")) {
			List<OrderItem> orderItems=orders.getOrderItems();
			for (OrderItem orderItem : orderItems) {
				InventoriesForm form=new InventoriesForm();
				
				List<Inventories> list=variantService.getVariantByID(orderItem.getTypeOfVariant()).getInventories();
				//chắc chắn phải có inventory của variant đó đầu tiên thì code mới ko lỗi
				form.setChange_amount(list.get(list.size()-1).getChange_amount()+orderItem.getQuantity());
				form.setInventoryVariant(orderItem.getTypeOfVariant());
				form.setEvent_type("Trả_hàng");
				form.setOrder_id(orders.getOrders_id());
				form.setAmount(orderItem.getQuantity());
				inventoryService.createInventory(form);
			}
		}
		orders.setStatus(status);
		return service.save(orders);
	}

}
