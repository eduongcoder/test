package com.example.Service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.OrderItem;
import com.example.Entity.OrderItemDTO;
import com.example.Entity.OrderItemDTOVariant;
import com.example.Entity.Orders;
import com.example.From.OrdersForm;
import com.example.Repository.IOrderRepository;

@Service
public class OrderService implements IOrderService {

	@Autowired
	private IOrderRepository service;

//	@Autowired
//	private OrderItemService serviceOrderItem;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<Orders> getallOrders() {
		return service.findAll();
	}

	@Override
	public Orders createOrder(OrdersForm form) {
		try {
			Orders orders = modelMapper.map(form, Orders.class);
//			System.out.println("Orders"+orders);
//			System.out.println("Form"+form);
			service.save(orders);
			return orders;
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public void deleteOrder(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean updateOrder(OrdersForm form) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Orders getOrderPrepare(int id) {
		List<Orders> list=getallOrders();
		
		for (Orders orders : list) {
			if (orders.getAccount().getAccount_id()==id && orders.getStatus().equals("Prepare")) {
				return orders;
			}
		}
		return null;
	}

	@Override
	public Orders getOrderByID(int id) {
		
		return service.findById(id).get();
	}

}