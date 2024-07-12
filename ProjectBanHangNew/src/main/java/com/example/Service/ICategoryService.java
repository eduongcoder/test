package com.example.Service;

import java.util.List;


import com.example.Entity.Category;
import com.example.From.CategoryForm;

public interface ICategoryService {

	public Category updateCategory(CategoryForm form);

	public void deleteCategoryByID(int id);

	public List<Category> getAllCategory();

	public Category createCategory(CategoryForm form);

	public Category findCategoryByID(int id);
	
	public int getSalePrice(int id);
}
