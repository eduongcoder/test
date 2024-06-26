package com.example.Service;

import java.util.List;

import com.example.Entity.TypeOfProduct;
import com.example.From.TypeOfProductForm;

public interface ITypeOfProductService {
	public List<TypeOfProduct> getAllTypeOfProduct();
	public TypeOfProduct getTypeOfProductByID(int id);
	public boolean deleteTypeOfProduct(int id);
	public TypeOfProduct createTypeOfProduct(TypeOfProductForm form);

	public boolean updateTypeOfProduct(TypeOfProductForm form);
}
