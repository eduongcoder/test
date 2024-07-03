package com.example.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.Product;
import com.example.Entity.ProductVersion;
import com.example.Service.IProductService;
import com.example.Service.IProductVersionService;

@RequestMapping("/api/statistic")
@RestController
public class StatisticsController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IProductService productService;

	@Autowired
	private IProductVersionService productVersionService;

	@GetMapping("/puchase")
	private Map<String, Map<Integer, Integer>> getTotalProduct(@RequestParam LocalDate startDate,
			@RequestParam LocalDate endDate) {
		Map<String, Map<Integer, Integer>> myMap = new TreeMap<>();
		List<Product> products = productService.getAllProducts();
		for (Product product : products) {
			List<ProductVersion> productVersion = product.getProductVersion();
			for (ProductVersion version : productVersion) {
				if (version.getCreated_at().toLocalDate().isAfter(startDate)
						&& version.getCreated_at().toLocalDate().isBefore(endDate)) {
					String date = version.getCreated_at().toLocalDate().toString();
					myMap.computeIfAbsent(date, k -> new HashMap<>()).merge(product.getProduct_id(),
							version.getQuantity_in_stock(), Integer::sum);
				}
				;
			}
		}
		return myMap;

	}
//	
////	@GetMapping("/stock")
////	private Map<K, V>
}
