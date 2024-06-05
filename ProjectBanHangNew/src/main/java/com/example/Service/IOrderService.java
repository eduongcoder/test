package com.example.Service;

import java.util.List;

import com.example.Entity.Orders;
import com.example.From.OrdersForm;

public interface IOrderService {
	public List<Orders> getallOrders();

	public Orders getOrderPrepare(int id);

	public Orders getOrderByID(int id);

	public Orders createOrder(OrdersForm form);

	public void deleteOrder(int id);

	public boolean updateOrder(OrdersForm form);
}