package com.example.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Category;
import com.example.Entity.Sales;
import com.example.Entity.Variant;
import com.example.From.SaleForm;
import com.example.Repository.ISaleRepository;

@Service
public class SaleService implements ISaleService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ISaleRepository service;

	@Autowired
	private IVariantService variantService;

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
	public Sales createSale(SaleForm form) {
		Variant  variant=variantService.getVariantByID(form.getVariant());
		Sales sales= new Sales();
		
		LocalDate start=LocalDate.parse(form.getSale_date_start());
		LocalDate end=null;
		if(form.getSale_date_end()!=null) {
			 end=LocalDate.parse(form.getSale_date_end());
		}
		
		
		try {
			sales.setQuantity(form.getQuantity());
			sales.setVariant_id(variant);
			sales.setSale_price(form.getSale_price());
			sales.setSale_base_price(form.getSale_base_price());
			sales.setSale_date_start(start.atStartOfDay());
			if (end!=null) {
				sales.setSale_date_end(end.atTime(23,59,59));
			}else {
				sales.setSale_date_end(null);
			}
			
			return service.save(sales);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public int getSalePrice(int idProductVersion) {
		Variant variant = variantService.getVariantByID(idProductVersion);
		List<Sales> listsSales = variant.getSales();
		
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
