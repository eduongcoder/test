package com.example.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Product;
import com.example.From.ProductForm;
import com.example.Repository.IProductRepository;

@Service
public class ProductService implements IProductService {

	@Autowired
	private IProductRepository service;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return service.findAll();
	}

	@Override
	public Product getProductByID(int id) {
		// TODO Auto-generated method stub
		return service.findById(id).get();
	}

	@Override
	public int deleteProduct(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Product updateProduct(ProductForm form) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
