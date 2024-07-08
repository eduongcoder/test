package com.example.Service;

import java.util.List;

import com.example.Entity.ProductVersion;
import com.example.Entity.Variant;
import com.example.Entity.VariantDTO;
import com.example.From.ProductVersionForm;

public interface IProductVersionService {
	public List<ProductVersion> getAllProductVersion();

	public ProductVersion getProductVersionByID(int id);

	public ProductVersion createProductVersion(ProductVersionForm form);

	public void deleteProductVersion(int id);

	public ProductVersion updateProductVersion(ProductVersionForm form);
	
	public ProductVersion getIdItemProductVersion(int idproduct);
	
	public List<VariantDTO> fillerStockVariant(int id);
}
