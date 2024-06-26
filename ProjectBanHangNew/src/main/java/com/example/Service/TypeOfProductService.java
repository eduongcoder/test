package com.example.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.ProductDTO;
import com.example.Entity.TypeOfProduct;
import com.example.From.TypeOfProductForm;
import com.example.Repository.ITypeOfProductRepository;

@Service
public class TypeOfProductService implements ITypeOfProductService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IProductService productService;

	@Autowired
	private ITypeOfProductRepository service;

	@Override
	public List<TypeOfProduct> getAllTypeOfProduct() {

		return service.findAll();
	}

	@Override
	public TypeOfProduct getTypeOfProductByID(int id) {
		// TODO Auto-generated method stub
		return service.findById(id).get();
	}

	@Override
	public boolean deleteTypeOfProduct(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateTypeOfProduct(TypeOfProductForm form) {
		return false;

	}

	@Override
	public TypeOfProduct createTypeOfProduct(TypeOfProductForm form) {
		try {

			TypeOfProduct typeOfProduct = modelMapper.map(form, TypeOfProduct.class);
			
			return service.save(typeOfProduct);
		} catch (Exception e) {
			return null;
		}
	}

}
