package com.example;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entity.Clothes;
import com.example.Entity.ClothesDTO;
import com.example.Entity.DepartmentDTO;
import com.example.Service.IClothesService;

//import com.example.Entity.Clothes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequestMapping("/api/clothes")
@RestController
public class ClothesController {
	@Autowired
	private IClothesService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping(value = "/{id}")
	public Clothes getbyId(@PathVariable(name="id") int id) {
		Clothes clothes= service.getEmployeeByID(id);
		return clothes;
	}
	@GetMapping
	public List<ClothesDTO> getAll() {
		List<Clothes> list=service.getAllEmployees();
		List<ClothesDTO> dtos=modelMapper.map(list, new TypeToken<List<ClothesDTO>>()
		{}.getType());
		return dtos;
	}

}
