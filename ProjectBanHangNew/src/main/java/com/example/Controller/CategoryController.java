package com.example.Controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.HistoryCategory;
import com.example.Entity.HistoryCategoryDTO;
import com.example.Service.ICategoryService;
import com.example.Service.IHistoryCategoryService;



@RequestMapping("/api/category")
@RestController
public class CategoryController {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired 
	private ICategoryService categoryService;
	
	@Autowired
	private IHistoryCategoryService historyCategoryService;
	
	@GetMapping(value = "/gethistory/{id}")
	public List<HistoryCategoryDTO> getHistoryCategory(@PathVariable(name = "id")int id){
		List<HistoryCategory> list=categoryService.findCategoryByID(id).getHistoryCategories();
		List<HistoryCategoryDTO> dtos = modelMapper.map(list, new TypeToken<List<HistoryCategoryDTO>>() {
		}.getType());
		return dtos;
	}
	
	@GetMapping(value = "/getchangeprice/{id}")
	public Map<LocalDateTime, Integer> getChangePrice(@PathVariable(name = "id")int id){
		Map<LocalDateTime, Integer>myMap=new LinkedHashMap<LocalDateTime, Integer>();
		List<HistoryCategory> list=categoryService.findCategoryByID(id).getHistoryCategories();
		boolean flag=true;
		for (HistoryCategory historyCategory : list) {
			if (flag==true) {
				myMap.put(categoryService.findCategoryByID(id).getDate_create(), historyCategory.getOld_price());
				flag=false;
			}
			myMap.put(historyCategory.getDate_update(), historyCategory.getNew_price());
		}
		return myMap;
	}
}
