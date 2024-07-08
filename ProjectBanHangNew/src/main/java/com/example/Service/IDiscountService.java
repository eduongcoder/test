package com.example.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.Entity.Discount;
import com.example.Entity.ProductShowDTOVersion3;
import com.example.From.DiscountForm;

public interface IDiscountService {
	public List<Discount> getAllDiscount();
	public Discount getDiscountByID(int id);
	public Discount createDiscount(DiscountForm form);
	public int getTimeDiscount(int variantID );
	
	public List<ProductShowDTOVersion3> getProductShowDTOVersion3(int id);
}
