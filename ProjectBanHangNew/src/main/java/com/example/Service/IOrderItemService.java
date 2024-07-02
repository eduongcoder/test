package com.example.Service;

import java.util.List;

import com.example.Entity.OrderItem;
import com.example.Enum.SizeEnum;
import com.example.From.OrderitemForm;

public interface IOrderItemService {
	public List<OrderItem> getAllOrderItems();

	public OrderItem createOrderItem(OrderitemForm form, int idOrder, int idProductVersion);

	public List<OrderItem> findbyIDOrdersItem(int id);

	public OrderItem findbyID(int id);

	public OrderItem updateOrderItem(OrderitemForm form);

	public boolean updateOrderItemQuantity(int id, int quantity);

	public boolean deleteOrderItem(int id);
	
	public boolean deleteOrderItemAll(int orderId);
	
	public SizeEnum getSizeEnum(int idVariant);
	
	public String getColor(int idVariant);
}
