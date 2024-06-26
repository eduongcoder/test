package com.example.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.ProductVersion;
import com.example.Entity.Sales;
import com.example.From.SaleForm;
import com.example.Repository.ISaleRepository;

@Service
public class SaleService implements ISaleService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ISaleRepository service;

	@Autowired
	private IProductVersionService productVersionService;

	@Override
	public List<Sales> getAllList() {
		// TODO Auto-generated method stub
		return service.findAll();
	}

	@Override
	public Sales getSaleByID(int id) {
		// TODO Auto-generated method stub
		return service.findById(id).get();
	}

	@Override
	public boolean deleteSales(int id) {
		try {
			service.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public Sales createSize(SaleForm form) {
		try {
			Sales sales = modelMapper.map(form, Sales.class);

			return service.save(sales);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public int getSalePrice(int idProductVersion) {
		ProductVersion productVersion = productVersionService.getProductVersionByID(idProductVersion);
		List<Sales> listsSales = productVersion.getSales();
		
		LocalDateTime now = LocalDateTime.now();
        LocalDateTime closestEndTime = null;
        int idSale = 0;
		for (Sales sales : listsSales) {
			LocalDateTime saleStart = sales.getSale_date_start();
            LocalDateTime saleEnd = sales.getSale_date_end();
			
			
            if (now.isAfter(saleStart) && now.isBefore(saleEnd)) {
                if (closestEndTime == null || saleEnd.isBefore(closestEndTime)) {
                    closestEndTime = saleEnd;
                    idSale = sales.getId();
                }
            }
		}
		if (idSale!=0) {
			return getSaleByID(idSale).getSale_price();
		}
		
		for (Sales sales : listsSales) {
			if (sales.getSale_date_end() == null) {
				return sales.getSale_price();
			}
		}
		return 10;
	}

}
