package com.example.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Category;
import com.example.Entity.Inventories;
import com.example.Entity.Variant;
import com.example.From.CategoryForm;
import com.example.From.HistoryCategoryForm;
import com.example.Repository.ICategoryRepository;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	private ICategoryRepository service;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IColorService colorService;

	@Autowired
	private ISizeService sizeService;

	@Autowired
	private ISaleService saleService;
	
	@Autowired
	private IProductService productService;

	@Override
	public Category updateCategory(CategoryForm form) {
		Category category = modelMapper.map(form, Category.class);
		category.setCatetoryColor(colorService.getColorByID(form.getCatetoryColor()));
		category.setCatetorySize(sizeService.getSizeByID(form.getCatetorySize()));
		category.setCatetoryProduct(productService.getProductByID(form.getCatetoryProduct()));
		category.setIsdelete(form.getIsdelete());
		return service.save(category);
	}

	@Override
	public void deleteCategoryByID(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		return service.findAll();
	}

	@Override
	public Category createCategory(CategoryForm form) {
		Category category = modelMapper.map(form, Category.class);
		category.setCatetoryColor(colorService.getColorByID(form.getCatetoryColor()));
		category.setCatetorySize(sizeService.getSizeByID(form.getCatetorySize()));
		category.setCatetoryProduct(productService.getProductByID(form.getCatetoryProduct()));
		return service.save(category);
	}

	@Override
	public Category findCategoryByID(int id) {
		// TODO Auto-generated method stub
		return service.findById(id).get();
	}

	@Override
	public int getSalePrice(int id) {
		Category category = findCategoryByID(id);
		int price_sale = 0;
		List<Variant> variants = category.getCatetoryColor().getVariants();
		for (Variant variant : variants) {
			int variantColorID = variant.getColor().getColor_id(),
					categoryColorID = category.getCatetoryColor().getColor_id();
			int variantSizeID = variant.getSize().getSize_id(),
					categorySizeID = category.getCatetorySize().getSize_id();
			int variantProductID = variant.getProductversion().getProduct().getProduct_id(),
					categoryProductID = category.getCatetoryProduct().getProduct_id();
			
			if (variantColorID == categoryColorID && categorySizeID == variantSizeID
					&& variantProductID == categoryProductID) {
				if (variant.getInventories()!=null && !variant.getInventories().isEmpty()) {
					List<Inventories> inventories=variant.getInventories();
					int index=inventories.size()-1;
					 if ( inventories.get(index).getChange_amount()>0 ) {
						  price_sale= saleService.getSalePrice(variant.getVariants_id());
						  return price_sale;
					}
				}
					
				
			}
		}
		return price_sale;
	}

}
