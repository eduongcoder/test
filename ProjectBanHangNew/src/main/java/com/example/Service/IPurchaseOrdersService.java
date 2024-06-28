package com.example.Service;

import java.util.List;

import com.example.Entity.PurchaseOrders;
import com.example.From.PurchaseOrdersForm;

public interface IPurchaseOrdersService {
	public List<PurchaseOrders> getAllPurchaseOrders();

	public PurchaseOrders getPurchaseOrdersByID(int id);

	public PurchaseOrders createPurchaseOrders(PurchaseOrdersForm form);

	public PurchaseOrders updatePurchaseOrders(PurchaseOrdersForm form);

	public PurchaseOrders deletePurchaseOrders(int id);

	public PurchaseOrders shippingOrders(int id);

	public PurchaseOrders creatPurchaseOrdersPerpare(PurchaseOrdersForm form);
}
