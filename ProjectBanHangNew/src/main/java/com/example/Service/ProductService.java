package com.example.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Entity.Product;

import com.example.From.ProductForm;

import com.example.From.TypeProductVersionSizeColorVariantForm;
import com.example.Repository.IProductRepository;

@Service
public class ProductService implements IProductService {

	@Autowired
	private IProductRepository service;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ITypeOfProductGenderService typeOfProductGenderService;
	
	@Autowired
	private ITypeOfProductNewService typeOfProductNewService;
	
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

	@Override
	public Product createProduct(ProductForm form) {
		Product product = modelMapper.map(form, Product.class);
		product.setTypeOfProductGender(typeOfProductGenderService.getSizeByID(form.getTypeOfProductGender()));
		product.setTypeOfProductNew(typeOfProductNewService.getSizeByID(form.getTypeOfProductNew()));
		return service.save(product);
	}



}
