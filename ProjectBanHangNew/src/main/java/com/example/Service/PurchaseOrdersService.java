package com.example.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.PurchaseOderItems;
import com.example.Entity.PurchaseOrders;
import com.example.Entity.Variant;
import com.example.From.PurchaseOrdersForm;
import com.example.Repository.IPurchaseOrdersRepositoty;
import com.example.Repository.IVariantRepository;

@Service
public class PurchaseOrdersService implements IPurchaseOrdersService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IPurchaseOrdersRepositoty service;

	@Autowired
	private IPurchaseOderItemsService purchaseOderItemsService;
	
	@Autowired
	private IVariantService variantService;
	
	@Autowired
	private IVariantRepository variantRepository;
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
		purchaseOrders.setStatus("Prepare");
		return service.save(purchaseOrders);
	}

	@Override
	public PurchaseOrders updatePurchaseOrders(PurchaseOrdersForm form) {
		PurchaseOrders purchaseOrders=getPurchaseOrdersByID(form.getPurchase_orders_id());
		purchaseOrders.setTotal_amount(form.getTotal_amount());
		purchaseOrders.setStatus("Shipping");
		return service.save(purchaseOrders);
	}

	@Override
	public PurchaseOrders deletePurchaseOrders(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PurchaseOrders shippingOrders(int id) {
		PurchaseOrders purchaseOrders=getPurchaseOrdersByID(id);
		purchaseOrders.setStatus("Shipping");
		return service.save(purchaseOrders);
	}

	@Override
	public PurchaseOrders creatPurchaseOrdersPerpare(PurchaseOrdersForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PurchaseOrders checkPrepare() {
		List<PurchaseOrders> list=getAllPurchaseOrders();
		for (PurchaseOrders purchaseOrders : list) {
			if (purchaseOrders.getStatus().equals("Prepare")) {
				return purchaseOrders;
			}
		}
		return null;
	}

	@Override
	public PurchaseOrders receiveOrder(int id) {
		PurchaseOrders purchaseOrders=getPurchaseOrdersByID(id);
		List<PurchaseOderItems> list=purchaseOrders.getPurchaseorderitem();
		for (PurchaseOderItems purchaseOderItems : list) {
			Variant variant=variantService.getVariantByID(purchaseOderItems.getVariant());
			variant.setQuantity_in_stock(purchaseOderItems.getQuantity_real());
			variantRepository.save(variant);
		}
		purchaseOrders.setStatus("Receive");
		return service.save(purchaseOrders);
	}
}
