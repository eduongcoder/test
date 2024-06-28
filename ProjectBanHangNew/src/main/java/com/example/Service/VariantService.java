package com.example.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.Entity.Category;
import com.example.Entity.Color;
import com.example.Entity.Variant;
import com.example.From.VariantForm;
import com.example.Repository.IVariantRepository;

@Service
public class VariantService  implements IVariantService{

	@Autowired
	private IVariantRepository service;
	
	@Autowired
	private ISizeService sizeService;
	
	@Autowired
	private IColorService colorService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private IProductVersionService productVersionService;
	
	@Autowired
	private ModelMapper modelMapper;
	

	
	@Override
	public List<Variant> getAllVariants() {
		// TODO Auto-generated method stub
		return service.findAll();
	}

	@Override
	public Variant getVariantByID(int id) {
		// TODO Auto-generated method stub
		return service.findById(id).get();
	}

	@Override
	public int deleteVariant(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Variant updateVariant(VariantForm form) {
		Variant variant=modelMapper.map(form, Variant.class);
		variant.setProductversion(productVersionService.getProductVersionByID(form.getProductversion()));
		variant.setColor(colorService.getColorByID(form.getColorID()));
		variant.setSize(sizeService.getSizeByID(form.getSizeID()));
		return service.save(variant);
	}

	@Override
	public Variant createVariant(VariantForm form) {

		Variant variant=modelMapper.map(form, Variant.class);
		variant.setProductversion(productVersionService.getProductVersionByID(form.getProductversion()));
		variant.setColor(colorService.getColorByID(form.getColorID()));
		variant.setSize(sizeService.getSizeByID(form.getSizeID()));
		
		return service.save(variant);
	}

	@Override
	public int getPriceFormCategory(int id) {
		Category category=categoryService.findCategoryByID(id);
		return category.getPrice_base();
	}

}
