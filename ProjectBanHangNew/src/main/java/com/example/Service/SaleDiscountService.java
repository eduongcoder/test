package com.example.Service;

import java.math.BigDecimal;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Discount;
import com.example.Entity.DiscountOnlyDTO;
import com.example.Entity.Product;
import com.example.Entity.ProductShowDTOVersion3;
import com.example.Entity.SaleDiscount;
import com.example.Entity.SaleDiscountId;
import com.example.From.SaleDiscountForm;
import com.example.Repository.ISaleDiscountRepository;

@Service
public class SaleDiscountService implements ISaleDiscountService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ISaleDiscountRepository service;

	@Autowired
	private ISaleService saleService;

	@Autowired
	private IDiscountService discountService;

	@Override
	public List<SaleDiscount> getAllSaleDiscounts() {
		// TODO Auto-generated method stub
		return service.findAll();
	}

	@Override
	public SaleDiscount creatSaleDiscount(SaleDiscountForm form) {
		SaleDiscount saleDiscount = new SaleDiscount();
		saleDiscount.setSales(saleService.getSaleByID(form.getSales()));
		saleDiscount.setDiscount(discountService.getDiscountByID(form.getDiscount()));
		return service.save(saleDiscount);
	}

	@Override
	public SaleDiscount getSaleDiscountById(SaleDiscountId SaleDiscountId) {
		// TODO Auto-generated method stub
		return service.findById(SaleDiscountId).get();
	}

	@Override
	public ProductShowDTOVersion3 getProductShowDTOVersion3(int id) {
		Product product = saleService.getSaleByID(id).getVariant_id().getProductversion().getProduct();
		return modelMapper.map(product, ProductShowDTOVersion3.class);
	}

	@Override
	public int getTotalRevenue(int saleid, int discountid) {
		SaleDiscountId saleDiscountId=new SaleDiscountId();
		saleDiscountId.setDiscount(discountid);
		saleDiscountId.setSales(saleid);
		SaleDiscount saleDiscount = getSaleDiscountById(saleDiscountId);
		BigDecimal salebase = BigDecimal.valueOf(saleDiscount.getSales().getSale_base_price());
		BigDecimal percent = saleDiscount.getDiscount().getPercent();
		BigDecimal result = salebase.subtract(percent.multiply(BigDecimal.valueOf(100)));
		int resultInt=result.intValue()*saleDiscount.getQuantitySaleDiscount();
		return resultInt;
	}

	@Override
	public SaleDiscount updateSaleDiscount(int saleid, int discountid, int quantity) {
		SaleDiscountId saleDiscountId=new SaleDiscountId();
		saleDiscountId.setDiscount(discountid);
		saleDiscountId.setSales(saleid);
		SaleDiscount saleDiscount = getSaleDiscountById(saleDiscountId);
		saleDiscount.setQuantitySaleDiscount(quantity+saleDiscount.getQuantitySaleDiscount());
		
		return service.save(saleDiscount);
	}

	@Override
	public DiscountOnlyDTO getDiscountOnlyDTO(int idDiscount) {
		Discount discount=discountService.getDiscountByID(idDiscount);
		DiscountOnlyDTO dto=modelMapper.map(discount, DiscountOnlyDTO.class);
		return dto;
	}
}
