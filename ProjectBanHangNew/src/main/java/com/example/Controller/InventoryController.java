package com.example.Controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.Inventories;
import com.example.Entity.InventoriesDTO;
import com.example.Entity.Orders;
import com.example.Entity.OrdersDTO;
import com.example.From.InventoriesForm;
import com.example.Service.IInventoryService;

@RequestMapping("/api/inventory")
@RestController
public class InventoryController {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IInventoryService inventoryService;
	
	@PostMapping("/creatInventory")
	private int creatInventory(@RequestBody InventoriesForm form) {
		return inventoryService.createInventory(form).getInventory_id();
	}
	
	@GetMapping
	private List<InventoriesDTO> getAll(){
		List<Inventories> list = inventoryService.getAllInventories();
		List<InventoriesDTO> dtos = modelMapper.map(list, new TypeToken<List<InventoriesDTO>>() {
		}.getType());
		return dtos;
	}
}
