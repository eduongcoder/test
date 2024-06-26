package com.example.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Images;
import com.example.Entity.ImagesDTO;
import com.example.Entity.PurchaseOderItems;
import com.example.Entity.SalesDTO;
import com.example.Entity.Variant;
import com.example.Enum.SizeEnum;
import com.example.From.PurchaseOderItemsForm;
import com.example.Repository.IPurchaseOderItemsRepository;

@Service
public class PurchaseOderItemsService implements IPurchaseOderItemsService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IPurchaseOderItemsRepository service;
	
	@Autowired
	private IVariantService variantService;

	@Override
	public List<PurchaseOderItems> getAllPurchaseOderItems() {
		// TODO Auto-generated method stub
		return service.findAll();
	}

	@Override
	public PurchaseOderItems getAllPurchaseOderItemsByID(int id) {
		// TODO Auto-generated method stub
		return service.findById(id).get();
	}

	@Override
	public PurchaseOderItems updatePurchaseOderItems(PurchaseOderItemsForm form) {
		PurchaseOderItems purchaseOderItems = modelMapper.map(form, PurchaseOderItems.class);
		return service.save(purchaseOderItems);
	}

	@Override
	public PurchaseOderItems deletePurchaseOderItems(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PurchaseOderItems createPurchaseOderItemsByID(PurchaseOderItemsForm form) {
		PurchaseOderItems purchaseOderItems = modelMapper.map(form, PurchaseOderItems.class);
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
	public List<ImagesDTO> getImages(int idvariant) {
		Variant variant=variantService.getVariantByID(idvariant);
		List<Images> list= variant.getImages();
		List<ImagesDTO> dtos = modelMapper.map(list, new TypeToken<List<ImagesDTO>>() {
		}.getType());
		return dtos;
	}

}
