package com.example.Service;

import java.util.List;

import com.example.Entity.ClientProduct;
import com.example.Entity.ClientProductId;
import com.example.From.ClientProductForm;

public interface IClientProductService {
	public List<ClientProduct> getAllClientProduct();
	public List<ClientProduct> getClientProductByID(int id);
	public ClientProduct createClientProduct(ClientProductForm form);
	public ClientProductId deleteClientProduct(ClientProductId id);
}
