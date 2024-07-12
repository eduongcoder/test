package com.example.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Inventories;
import com.example.From.InventoriesForm;
import com.example.Repository.InventoryRepository;

@Service
public class InventoryService implements IInventoryService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private InventoryRepository service;
	
	@Autowired
	private IVariantService variantService;
	
	@Override
	public List<Inventories> getAllInventories() {
		// TODO Auto-generated method stub
		return service.findAll();
	}

	@Override
	public Inventories getInventoriesById(int id) {
		// TODO Auto-generated method stub
		return service.findById(id).get();
	}

	@Override
	public Inventories createInventory(InventoriesForm form) {
		Inventories inventories=modelMapper.map(form, Inventories.class);
		inventories.setOrder_id(form.getOrder_id());
		inventories.setInventoryVariant(variantService.getVariantByID(form.getInventoryVariant()));
		inventories.setEvent_date(LocalDateTime.now());
		return service.save(inventories);
	}

	@Override
	public int updateInventory(InventoriesForm form) {
		Inventories inventories=getInventoriesById(form.getInventory_id());
		inventories.setAmount(form.getAmount());
		inventories.setChange_amount(form.getChange_amount());
		inventories.setEvent_date(LocalDateTime.now());
		inventories.setEvent_type(form.getEvent_type());
		inventories.setOrder_id(form.getOrder_id());
		inventories.setInventoryVariant(variantService.getVariantByID(form.getInventoryVariant()));
		
		return service.save(inventories).getInventory_id();
	}

	@Override
	public boolean deleteInventory(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
