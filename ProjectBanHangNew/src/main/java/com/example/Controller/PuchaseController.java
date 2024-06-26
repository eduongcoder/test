package com.example.Controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.PurchaseOderItemsDetailDTO;
import com.example.Entity.PurchaseOrders;
import com.example.Entity.PurchaseOrdersDTO;
import com.example.Entity.PurchaseOrdersDetailDTO;
import com.example.From.PurchaseOrdersForm;
import com.example.Service.IPurchaseOrdersService;


@RequestMapping("/api/puchase")
@RestController
public class PuchaseController {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IPurchaseOrdersService service;
	
	@GetMapping
	public List<PurchaseOrdersDTO> getAllPurchaseOrders(){
		List<PurchaseOrders> list=service.getAllPurchaseOrders();
		List<PurchaseOrdersDTO> dtos = modelMapper.map(list, new TypeToken<List<PurchaseOrdersDTO>>() {
		}.getType());
		return dtos;
	}
	
	@GetMapping("/detail")
	public List<PurchaseOrdersDetailDTO> getAllPurchaseOrdersDetail(){
		List<PurchaseOrders> list=service.getAllPurchaseOrders();
		List<PurchaseOrdersDetailDTO> dtos = modelMapper.map(list, new TypeToken<List<PurchaseOrdersDetailDTO>>() {
		}.getType());
		return dtos;
	}
	
	@GetMapping("/{id}")
	public PurchaseOrdersDTO gePurchaseOrdersByID(@PathVariable(name = "id") int id) {
		PurchaseOrders purchaseOrders=service.getPurchaseOrdersByID(id);
		PurchaseOrdersDTO dtos = modelMapper.map(purchaseOrders, PurchaseOrdersDTO.class);
		return dtos;
	}
	
	@PostMapping("/create")
	public PurchaseOrdersDTO createPurchaseOrder(@RequestBody PurchaseOrdersForm form) {
		PurchaseOrders purchaseOrders=service.createPurchaseOrders(form);
		PurchaseOrdersDTO dtos = modelMapper.map(purchaseOrders, PurchaseOrdersDTO.class);
		return dtos;
	}

	@PutMapping("/update")
	public PurchaseOrdersDTO updatePurchaseOrders(@RequestBody PurchaseOrdersForm form) {
		PurchaseOrders purchaseOrders=service.updatePurchaseOrders(form);
		PurchaseOrdersDTO dtos = modelMapper.map(purchaseOrders, PurchaseOrdersDTO.class);
		return dtos;
	}
}