package com.example.Service;

import java.util.List;

import com.example.Entity.Sales;
import com.example.From.SaleForm;

public interface ISaleService {
	public List<Sales> getAllList();
	public Sales getSaleByID(int id);
	public boolean deleteSales(int id);
	public Sales createSale(SaleForm form);
	public int getSalePrice(int idProductVersion);
}
