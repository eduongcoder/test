package com.example.Service;

import java.util.List;

import com.example.Entity.DiscountOnlyDTO;
import com.example.Entity.ProductShowDTOVersion3;
import com.example.Entity.SaleDiscount;
import com.example.Entity.SaleDiscountId;
import com.example.From.SaleDiscountForm;

public interface ISaleDiscountService {
	public List<SaleDiscount> getAllSaleDiscounts();
	public SaleDiscount getSaleDiscountById(SaleDiscountId SaleDiscountId);
	public SaleDiscount creatSaleDiscount(SaleDiscountForm form);
	public ProductShowDTOVersion3 getProductShowDTOVersion3(int id);
	public int getTotalRevenue(int saleid, int discountid);
	public SaleDiscount updateSaleDiscount(int saleid, int discountid,int quantity);
	public DiscountOnlyDTO getDiscountOnlyDTO(int idDiscount);
	public SaleDiscountId getSaleDiscountId(int idSale);
	
	public SaleDiscount updateSaleDiscountAll(int saleid, int discountid,int quantity,int money);

}
