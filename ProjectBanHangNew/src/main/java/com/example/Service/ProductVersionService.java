package com.example.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.ProductVersion;
import com.example.From.ProductVersionForm;
import com.example.Repository.IProductVersionRepository;

@Service
public class ProductVersionService implements IProductVersionService {

		@Autowired
		private ModelMapper modelMapper;
		
		@Autowired
		private IProductVersionRepository service;

		@Override
		public List<ProductVersion> getAllProductVersion() {
			
			return service.findAll();
		}

		@Override
		public ProductVersion getProductVersionByID(int id) {
			
			return service.findById(id).get();
		}

		@Override
		public void deleteProductVersion(int id) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateProductVersion(ProductVersionForm form) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void createProductVersion(ProductVersionForm form) {
			// TODO Auto-generated method stub
			
		}
}