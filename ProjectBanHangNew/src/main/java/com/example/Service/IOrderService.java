package com.example.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.example.Entity.Orders;
import com.example.From.OrdersForm;

public interface IOrderService {
	public List<Orders> getallOrders();

	public Orders getOrderPrepare(int id);

	public Orders getOrderByID(int id);

	public Orders createOrder(OrdersForm form);

	public int createOrderPending(OrdersForm form);
	
	public void deleteOrder(int id);

	public Orders updateOrder(int idAccount,OrdersForm form);
	
	public boolean updateAdressOrder(int id,String address);
	
	public Map<LocalDate, Integer> totalOrderMoney();
	
	public Orders updateStatusOrders(int id, String status);
	
	public Orders updateMoneyOrders(int id);
}
