package com.example.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.PurchaseOrders;
import com.example.From.PurchaseOrdersForm;
import com.example.Repository.IPurchaseOrdersRepositoty;

@Service
public class PurchaseOrdersService implements IPurchaseOrdersService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IPurchaseOrdersRepositoty service;

	@Override
	public List<PurchaseOrders> getAllPurchaseOrders() {
		// TODO Auto-generated method stub
		return service.findAll();
	}

	@Override
	public PurchaseOrders getPurchaseOrdersByID(int id) {
		// TODO Auto-generated method stub
		return service.findById(id).get();
	}

	@Override
	public PurchaseOrders createPurchaseOrders(PurchaseOrdersForm form) {
		PurchaseOrders purchaseOrders = modelMapper.map(form, PurchaseOrders.class);

		return service.save(purchaseOrders);
	}

	@Override
	public PurchaseOrders updatePurchaseOrders(PurchaseOrdersForm form) {
		PurchaseOrders purchaseOrders = modelMapper.map(form, PurchaseOrders.class);

		return service.save(purchaseOrders);
	}

	@Override
	public PurchaseOrders deletePurchaseOrders(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
