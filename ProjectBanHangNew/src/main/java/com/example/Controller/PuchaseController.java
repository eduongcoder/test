package com.example.Controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.PurchaseOderItems;
import com.example.Entity.PurchaseOderItemsDetailDTO;
import com.example.Entity.PurchaseOrders;
import com.example.Entity.PurchaseOrdersDTO;
import com.example.Entity.PurchaseOrdersDetailDTO;
import com.example.From.PurchaseOderItemsForm;
import com.example.From.PurchaseOrdersForm;
import com.example.Service.IPurchaseOderItemsService;
import com.example.Service.IPurchaseOrdersService;

@RequestMapping("/api/puchase")
@RestController
public class PuchaseController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IPurchaseOrdersService service;

	@Autowired
	private IPurchaseOderItemsService purchaseOderItemsService;

	@GetMapping
	public List<PurchaseOrdersDTO> getAllPurchaseOrders() {
		List<PurchaseOrders> list = service.getAllPurchaseOrders();
		List<PurchaseOrdersDTO> dtos = modelMapper.map(list, new TypeToken<List<PurchaseOrdersDTO>>() {
		}.getType());
		return dtos;
	}

	@GetMapping("/detail")
	public List<PurchaseOrdersDetailDTO> getAllPurchaseOrdersDetail() {
		List<PurchaseOrders> list = service.getAllPurchaseOrders();
		List<PurchaseOrdersDetailDTO> dtos = modelMapper.map(list, new TypeToken<List<PurchaseOrdersDetailDTO>>() {
		}.getType());
		return dtos;
	}

	@GetMapping("/{id}")
	public PurchaseOrdersDTO gePurchaseOrdersByID(@PathVariable(name = "id") int id) {
		PurchaseOrders purchaseOrders = service.getPurchaseOrdersByID(id);
		PurchaseOrdersDTO dtos = modelMapper.map(purchaseOrders, PurchaseOrdersDTO.class);
		return dtos;
	}
	
	@GetMapping("/checkprepare")
	public int checkPrepare() {
		PurchaseOrders result=service.checkPrepare();
		if (result!=null) {
			return result.getPurchase_orders_id();
		}
		return -1;
	}
	

	@PostMapping("/create")
	public PurchaseOrdersDTO createPurchaseOrder(@RequestBody PurchaseOrdersForm form) {
		PurchaseOrders purchaseOrders = service.createPurchaseOrders(form);
		PurchaseOrdersDTO dtos = modelMapper.map(purchaseOrders, PurchaseOrdersDTO.class);
		return dtos;
	}

	@PostMapping("/createpurchase")
	public int createPurchaseOrderInt(@RequestBody PurchaseOrdersForm form) {
		PurchaseOrders purchaseOrders = service.createPurchaseOrders(form);
		return purchaseOrders.getPurchase_orders_id();
	}

	@PostMapping("/createpurchaseItem")
	public PurchaseOderItemsDetailDTO createPurchaseOrderItemInt(@RequestBody PurchaseOderItemsForm form) {
		PurchaseOderItems purchaseOrders = purchaseOderItemsService.createPurchaseOderItems(form);
		PurchaseOderItemsDetailDTO dto=modelMapper.map(purchaseOrders, PurchaseOderItemsDetailDTO.class);
		return dto;
	}

	@PutMapping("/update")
	public PurchaseOrdersDTO updatePurchaseOrders(@RequestBody PurchaseOrdersForm form) {
		PurchaseOrders purchaseOrders = service.updatePurchaseOrders(form);
		PurchaseOrdersDTO dtos = modelMapper.map(purchaseOrders, PurchaseOrdersDTO.class);
		return dtos;
	}
	
	@PutMapping(value = "/compelete/{id}")
	public PurchaseOrdersDTO updatePurchaseOrders(@PathVariable(name = "id") int id) {
		PurchaseOrders purchaseOrders = service.shippingOrders(id);
		PurchaseOrdersDTO dtos = modelMapper.map(purchaseOrders, PurchaseOrdersDTO.class);
		return dtos;
	} 
	@PutMapping("/updatePuchaseItem")
	public PurchaseOderItemsDetailDTO updatePurchaseOderItem(@RequestBody PurchaseOderItemsForm form) {
		return modelMapper.map(purchaseOderItemsService.updatePurchaseOderItems(form), PurchaseOderItemsDetailDTO.class);
	}
	@DeleteMapping(value = "/delete/{id}")
	public int deletePuchaseOrderItem(@PathVariable(value = "id")int id) {
		purchaseOderItemsService.deletePurchaseOderItems(id);
		return id;
	}
}
