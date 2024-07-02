package com.example.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Images;
import com.example.Entity.ImagesDTO;
import com.example.Entity.ImagesDTOOnlyID;
import com.example.Entity.Product;
import com.example.Entity.PurchaseOderItems;
import com.example.Entity.PurchaseOrders;
import com.example.Entity.SalesDTO;
import com.example.Entity.Variant;
import com.example.Enum.SizeEnum;
import com.example.From.PurchaseOderItemsForm;
import com.example.From.PurchaseOrdersForm;
import com.example.Repository.IPurchaseOderItemsRepository;

@Service
public class PurchaseOderItemsService implements IPurchaseOderItemsService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IPurchaseOderItemsRepository service;
	
	@Autowired
	private IVariantService variantService;

	@Autowired
	private IProductVersionService productVersionService;
	
	@Autowired
	private IPurchaseOrdersService purchaseOrdersService;
	
	@Autowired
	private IProductService productService;
	@Override
	public List<PurchaseOderItems> getAllPurchaseOderItems() {
		// TODO Auto-generated method stub
		return service.findAll();
	}

	@Override
	public PurchaseOderItems getPurchaseOderItemsByID(int id) {
		// TODO Auto-generated method stub
		return service.findById(id).get();
	}

	@Override
	public PurchaseOderItems updatePurchaseOderItems(PurchaseOderItemsForm form) {
		PurchaseOderItems purchaseOderItems = modelMapper.map(form, PurchaseOderItems.class);
		purchaseOderItems.setProductVersion(productVersionService.getProductVersionByID(form.getProductVersion()));
		purchaseOderItems.setPurchaseOrder(purchaseOrdersService.getPurchaseOrdersByID(form.getPurchaseOrder()));
		return service.save(purchaseOderItems);
	}

	@Override
	public PurchaseOderItems deletePurchaseOderItems(int id) {
		service.deleteById(id);
		return null;
	}

	@Override
	public PurchaseOderItems createPurchaseOderItems(PurchaseOderItemsForm form) {
		
		List<PurchaseOrders> list=purchaseOrdersService.getAllPurchaseOrders();
		PurchaseOrders temp=null;
		for (PurchaseOrders purchaseOrders : list) {
			if (purchaseOrders.getStatus().equals("Prepare")) {
				temp=purchaseOrders;
				break;
			}
		}
		if (temp==null) {
			PurchaseOrdersForm form1=new PurchaseOrdersForm();
			form1.setTotal_amount(0);
			temp=purchaseOrdersService.createPurchaseOrders(form1);
		}
		
		form.setPurchaseOrder(temp.getPurchase_orders_id());
		PurchaseOderItems purchaseOderItems = modelMapper.map(form, PurchaseOderItems.class);
		purchaseOderItems.setProductVersion(productVersionService.getProductVersionByID(form.getProductVersion()));
		purchaseOderItems.setPurchaseOrder(purchaseOrdersService.getPurchaseOrdersByID(form.getPurchaseOrder()));
		
		return service.save(purchaseOderItems);
	}

	@Override
	public SizeEnum getSizeVariant(int id) {
		Variant variant =variantService.getVariantByID(id);
		
		return variant.getSize().getSize_name();
	}

	@Override
	public String getColorVariant(int id) {
		Variant variant =variantService.getVariantByID(id);
		return variant.getColor().getColor_name();
	}

	@Override
	public List<ImagesDTOOnlyID> getImages(int idproduct) {
		Product product=productService.getProductByID(idproduct);
		List<Images> list= product.getImagesMap();
		List<ImagesDTOOnlyID> dtos = modelMapper.map(list, new TypeToken<List<ImagesDTOOnlyID>>() {
		}.getType());
		return dtos;
	}

	@Override
	public PurchaseOderItems updatePurchaseOderItemsQuantity(PurchaseOderItemsForm form) {
		PurchaseOderItems purchaseOderItems=getPurchaseOderItemsByID(form.getPurchase_order_items_id());
		purchaseOderItems.setQuantity_real(form.getQuantity_real());
		
		return service.save(purchaseOderItems);
	}

}
