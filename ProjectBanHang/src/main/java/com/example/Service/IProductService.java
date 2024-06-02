package com.example.Service;

import java.util.List;

import com.example.Entity.Product;
import com.example.From.ProductForm;

public interface IProductService {
	public List<Product> getAllProducts();
	public Product getProductByID(int id);
	public int deleteProduct(int id);
	public Product updateProduct(ProductForm form);
}
