package com.example.Service;

import java.util.List;

import com.example.Entity.Product;
import com.example.Entity.ProductShowDTO;
import com.example.From.ProductForm;
import com.example.From.TypeProductVersionSizeColorVariantForm;

public interface IProductService {
	public List<Product> getAllProducts();
	public Product getProductByID(int id);
	public int deleteProduct(int id);
	public Product updateProduct(ProductForm form);
	public Product createProduct(ProductForm form);
	public boolean createProductBig(TypeProductVersionSizeColorVariantForm form);

//	public List<ProductShow> getAllProductShow() {
}
