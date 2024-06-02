package com.example.Service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.ClientProduct;
import com.example.Entity.ClientProductDTO;
import com.example.Entity.ClientProductId;
import com.example.Entity.Product;
import com.example.From.ClientProductForm;
import com.example.Repository.IClientProductRepository;
@Service
public class ClientProductService implements IClientProductService {

	@Autowired
	private IClientProductRepository service;

	@Autowired
	private IProductService serviceProduct;
	
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public List<ClientProduct> getAllClientProduct() {
		return service.findAll();
	}

	@Override
	public List<ClientProduct>  getClientProductByID(int id) {
		// TODO Auto-generated method stub
		List<ClientProduct> list=new ArrayList<ClientProduct>();
		ClientProductId clientProductId=new ClientProductId();
		clientProductId.setAccountID(id);
		List<Product> listProducts=serviceProduct.getAllProducts();
		for (Product product : listProducts) {

			clientProductId.setVariantID(product.getProductid());
			try {
				ClientProduct temp=service.findById(clientProductId).get();
				if (temp!=null) {
					list.add(temp);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return list;
	}

	@Override
	public ClientProduct createClientProduct(ClientProductForm form) {

		ClientProduct clientProduct=modelMapper.map(form, ClientProduct.class);
		service.save(clientProduct);
		return clientProduct;
	}

	@Override
	public ClientProductId deleteClientProduct(ClientProductId id) {
		try {
			service.deleteById(id);
			
			return id;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	
}
