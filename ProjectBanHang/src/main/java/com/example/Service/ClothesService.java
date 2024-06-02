//package com.example.Service;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.Entity.Clothes;
//import com.example.Repository.IClothesRepository;
//
//@Service
//public class ClothesService implements IClothesService{
//
//	@Autowired
//	private IClothesRepository service;
//	
//	@Override
//	public Clothes getEmployeeByID(int id) {
//		return service.findById(id).get();
//	}
//
//	@Override
//	public List<Clothes> getAllEmployees() {
//		List<Clothes> list =service.findAll();
//		return list;
//	}
//
//}
