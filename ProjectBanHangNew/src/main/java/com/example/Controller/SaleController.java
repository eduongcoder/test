package com.example.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.Discount;
import com.example.Entity.DiscountDTO;
import com.example.Entity.SaleDiscount;
import com.example.Entity.SaleDiscountDTO;
import com.example.Entity.SaleIdListDTO;
import com.example.Entity.Sales;
import com.example.Entity.SalesDTO;
import com.example.From.DiscountForm;
import com.example.From.SaleDiscountForm;
import com.example.From.SaleForm;
import com.example.Service.ICategoryService;
import com.example.Service.IDiscountService;
import com.example.Service.ISaleDiscountService;
import com.example.Service.ISaleService;
import com.example.Service.IVariantService;

@RequestMapping("/api/sale")
@RestController
public class SaleController {
	@Autowired
	private ISaleService service;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ISaleDiscountService saleDiscountService;

	@Autowired
	private IDiscountService discountService;
	
	@PostMapping("/creatDiscount")
	private int creatDiscount(@RequestBody DiscountForm form) {
		if (form.getDate_end().isBefore(LocalDate.now())|| form.getDate_start().isBefore(LocalDate.now())) {
			return -1;
		}
		return discountService.createDiscount(form).getDiscount_id();
	}
	
	@PostMapping("/createSaleDiscount")
	private SaleDiscountDTO createSaleDiscount(@RequestBody SaleDiscountForm form) {
		return modelMapper.map(saleDiscountService.creatSaleDiscount(form), SaleDiscountDTO.class);
	}
	
	@PostMapping("/createSaleDiscountMulti")
	private List<SaleDiscountDTO> createSaleDiscountMulti(@RequestBody SaleIdListDTO list,@RequestParam int idDiscount){
		List<SaleDiscount> saleDiscount=new ArrayList<>();
		Set<Integer> listIntegers= new HashSet<>(list.getSaleID());
		for (Integer integer : listIntegers) {
			if (service.getSaleDiscount(integer, idDiscount)!=null) {
				
			}else {
				SaleDiscountForm form=new SaleDiscountForm();
				form.setDiscount(idDiscount);
				form.setSales(integer);
				saleDiscount.add(saleDiscountService.creatSaleDiscount(form));
			}
		}
		if (!saleDiscount.isEmpty()) {
			List<SaleDiscountDTO> dtos = modelMapper.map(saleDiscount, new TypeToken<List<SaleDiscountDTO>>() {
			}.getType());
			return dtos; 
		} 
		return null; 
	}
	
	@PutMapping("/updateQuantitySaleDiscount")
	private SaleDiscountDTO updateQuantitySaleDiscount(@RequestParam int saleid,@RequestParam int discountid,@RequestParam int quantity) {
		
		return modelMapper.map(saleDiscountService.updateSaleDiscount(saleid,discountid,quantity), SaleDiscountDTO.class);
	}
	
	@GetMapping("/getDiscount")
	private List<DiscountDTO> getDiscount(){
		List<Discount> list = discountService.getAllDiscount();
		List<DiscountDTO> dtos = modelMapper.map(list, new TypeToken<List<DiscountDTO>>() {
		}.getType());
		return dtos;
	}
	
	@GetMapping
	private List<SalesDTO> getAllSales() {
		List<Sales> list = service.getAllList();
		List<SalesDTO> dtos = modelMapper.map(list, new TypeToken<List<SalesDTO>>() {
		}.getType());
		return dtos;
	}

	@GetMapping(value = "/{id}")
	private SalesDTO getSalesByID(@PathVariable(name = "id") int id) {
		Sales sales = service.getSaleByID(id);
		if (sales == null) {
			return null;
		}
		return modelMapper.map(sales, SalesDTO.class);

	}

	@PostMapping("/create")
	private SalesDTO creatSales(@RequestBody SaleForm form) {

		Sales sales1 = service.createSale(form);

		if (sales1 == null) {
			return null;
		}

		return modelMapper.map(sales1, SalesDTO.class);
	}

	@DeleteMapping(value = "/delete/{id}")
	private boolean deleteSale(@PathVariable(name = "id") int id) {
		return service.deleteSales(id);
	}
	
	
}
