package com.example.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Discount;
import com.example.Entity.Product;
import com.example.Entity.ProductShowDTOVersion3;
import com.example.Entity.SaleDiscount;
import com.example.From.DiscountForm;
import com.example.Repository.IDiscountRepository;

@Service
public class DiscountService implements IDiscountService{
	
	@Autowired
	private IDiscountRepository service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<Discount> getAllDiscount() {
		// TODO Auto-generated method stub
		return service.findAll();
	}

	@Override
	public Discount getDiscountByID(int id) {
		// TODO Auto-generated method stub
		return service.findById(id).get();
	}

	@Override
	public Discount createDiscount(DiscountForm form) {
		LocalDateTime start=form.getDate_start().atStartOfDay();
		LocalDateTime end=form.getDate_end().atTime(23,59,59);
		Discount discount=modelMapper.map(form, Discount.class);
		discount.setDate_start(start);
		discount.setDate_end(end);
		return service.save(discount);
	}

	@Override
	public List<ProductShowDTOVersion3> getProductShowDTOVersion3(int id) {
		
		List<Product> list=new ArrayList<Product>();
		List<SaleDiscount> saleDiscounts=getDiscountByID(id).getSaleDiscount();
		for (SaleDiscount saleDiscount : saleDiscounts) {
			 list.add(saleDiscount.getSales().getVariant_id().getProductversion().getProduct()) ;
		}
		Set<Product> set=new LinkedHashSet<>(list);
		List<Product> list2=new ArrayList<>(set);
		List<ProductShowDTOVersion3> result=  modelMapper.map(list2, new TypeToken<List<ProductShowDTOVersion3>>() {
		}.getType());
		return result;
		
	}

	@Override
	public int getTimeDiscount(int variantID) {

		return 0;
	}
	
}
