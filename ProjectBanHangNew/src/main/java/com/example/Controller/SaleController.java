package com.example.Controller;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.ProductVersion;
import com.example.Entity.Sales;
import com.example.Entity.SalesDTO;
import com.example.From.SaleForm;
import com.example.Service.ICategoryService;
import com.example.Service.IProductVersionService;
import com.example.Service.ISaleService;

@RequestMapping("/api/sale")
@RestController
public class SaleController {
	@Autowired
	private ISaleService service;

	@Autowired
	private IProductVersionService productVersionService;
	
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping
	private List<SalesDTO> getAllSales() {
		List<Sales> list = service.getAllList();
		List<SalesDTO> dtos = modelMapper.map(list, new TypeToken<List<SalesDTO>>() {
		}.getType());
		return dtos;
	}
	
	@GetMapping(value = "/{id}")
	private SalesDTO getSalesByID(@PathVariable(name = "id")int id) {
		Sales sales=service.getSaleByID(id);
		return modelMapper.map(sales, SalesDTO.class);
	}
	
//	@PostMapping("/create")
//	private SalesDTO creatSales(@RequestBody SaleForm form) {
//		ProductVersion productVersion=productVersionService.getProductVersionByID(form.getProductVersion());
//		List<Sales> sales= productVersion.getSales();
//		for (Sales sale : sales) {
//
//		}
//		
//		Sales sales1=service.createSize(form);
//		return modelMapper.map(sales1, SalesDTO.class);
//	}
	
	@DeleteMapping(value = "/delete/{id}")
	private boolean deleteSale(@PathVariable(name = "id")int id) {
		return service.deleteSales(id);
	}
	
	@GetMapping(value = "/getsalechange/{id}")
	public Map<LocalDateTime, Integer> getSaleChange(@PathVariable(name = "id")int id){
		Map<LocalDateTime, Integer> myMap=new LinkedHashMap<>();
		List<Sales> list=categoryService.findCategoryByID(id).getSales();
		for (Sales sales : list) {
			myMap.put(sales.getSale_date_end(), sales.getSale_price());
		}
		return myMap;
		
	}
	
}
