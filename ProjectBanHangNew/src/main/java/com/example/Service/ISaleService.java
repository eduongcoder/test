package com.example.Service;

import java.math.BigDecimal;
import java.util.List;

import com.example.Entity.Sales;
import com.example.From.SaleForm;
import com.example.From.SizeForm;

public interface ISaleService {
	public List<Sales> getAllList();
	public Sales getSaleByID(int id);
	public boolean deleteSales(int id);
	public Sales createSize(SaleForm form);
	public int getSalePrice(int idProductVersion);
}
