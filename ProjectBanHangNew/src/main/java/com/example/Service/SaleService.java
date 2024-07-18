package com.example.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Category;
import com.example.Entity.Discount;
import com.example.Entity.SaleDiscount;
import com.example.Entity.SaleDiscountId;
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

	@Autowired
	private ISaleDiscountService saleDiscountService;

	@Autowired
	private IDiscountService discountService;
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
		Variant variant = variantService.getVariantByID(form.getVariant());
		Sales sales = new Sales();

		LocalDate start = LocalDate.parse(form.getSale_date_start());
		LocalDate end = null;
		if (form.getSale_date_end() != null) {
			end = LocalDate.parse(form.getSale_date_end());
		}

		try {
			sales.setQuantity(form.getQuantity());
			sales.setVariant_id(variant);
			sales.setSale_price(form.getSale_price());
			sales.setSale_base_price(form.getSale_base_price());
			sales.setSale_date_start(start.atStartOfDay());
			if (end != null) {
				sales.setSale_date_end(end.atTime(23, 59, 59));
			} else {
				sales.setSale_date_end(null);
			}

			return service.save(sales);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public int getSalePrice(int idVariant) {
		Variant variant = variantService.getVariantByID(idVariant);
		// Kiểm tra variant không null
		if (variant != null) {
			Sales sales = variant.getSales();

			// Kiểm tra sales không null
			if (sales != null) {
				List<SaleDiscount> listsSales = sales.getSaleDiscount();

				// Kiểm tra listsSales không null và không rỗng
				if (listsSales != null && !listsSales.isEmpty()) {
					LocalDateTime now = LocalDateTime.now();
					LocalDateTime closestEndTime = null;
					int idSale = 0, idDiscount = 0;

					// Lặp qua danh sách SaleDiscount
					for (SaleDiscount sale : listsSales) {
						LocalDateTime saleStart = sale.getDiscount().getDate_start();
						LocalDateTime saleEnd = sale.getDiscount().getDate_end();

						// Kiểm tra ngày hiện tại nằm trong khoảng saleStart và saleEnd
						if (saleEnd != null && now.isAfter(saleStart) && now.isBefore(saleEnd)) {
							if (closestEndTime == null || saleEnd.isBefore(closestEndTime)) {
								closestEndTime = saleEnd;
								idSale = sale.getSales().getId();
								idDiscount = sale.getDiscount().getDiscount_id();
							}
						}
					}

					// Kiểm tra có idSale khác 0
					if (idSale != 0) {
						SaleDiscountId saleDiscount = new SaleDiscountId();
						saleDiscount.setDiscount(idDiscount);
						saleDiscount.setSales(idSale);

						// Lấy giá trị sale_base_price từ Sales và tính toán giảm giá
						BigDecimal salebase = BigDecimal.valueOf(sales.getSale_base_price());
						BigDecimal percent = saleDiscountService.getSaleDiscountById(saleDiscount).getDiscount()
								.getPercent();
						BigDecimal result = salebase.subtract(salebase.multiply(percent));

						// Trả về kết quả là một số nguyên
						return result.intValue();
					}
					return sales.getSale_base_price();

				} else {
					return sales.getSale_base_price();
				}
			} else {
				return -5;
			}
		} else {
			return -10;
		}

	}

	@Override
	public Sales updateQuantitySales(int id, int quantity) {
		Sales sales=getSaleByID(id);
		sales.setQuantity(sales.getQuantity()+quantity);
		return service.save(sales);
	}

	@Override
	public SaleDiscount getSaleDiscount(int idSale,int idDiscount) {
		List<SaleDiscount> saleDiscounts=getSaleByID(idSale).getSaleDiscount();
		Discount discount=discountService.getDiscountByID(idDiscount);
		if (!saleDiscounts.isEmpty()) {
			for (SaleDiscount saleDiscount : saleDiscounts) {
				if (saleDiscount.getDiscount().getDate_start().isAfter(discount.getDate_start())|| saleDiscount.getDiscount().getDate_end().isBefore(discount.getDate_end())) {
					return  saleDiscount;
				}
			}
		}
		
		return null;
	}

	@Override
	public int getSaleBasePrice(int idVariant) {
		Variant variant = variantService.getVariantByID(idVariant);
		// Kiểm tra variant không null
		if (variant != null) {
			Sales sales = variant.getSales();

			// Kiểm tra sales không null
			if (sales != null) {
				List<SaleDiscount> listsSales = sales.getSaleDiscount();

				// Kiểm tra listsSales không null và không rỗng
				if (listsSales != null && !listsSales.isEmpty()) {
					LocalDateTime now = LocalDateTime.now();
					LocalDateTime closestEndTime = null;
					int idSale = 0, idDiscount = 0;

					// Lặp qua danh sách SaleDiscount
					for (SaleDiscount sale : listsSales) {
						LocalDateTime saleStart = sale.getDiscount().getDate_start();
						LocalDateTime saleEnd = sale.getDiscount().getDate_end();

						// Kiểm tra ngày hiện tại nằm trong khoảng saleStart và saleEnd
						if (saleEnd != null && now.isAfter(saleStart) && now.isBefore(saleEnd)) {
							if (closestEndTime == null || saleEnd.isBefore(closestEndTime)) {
								closestEndTime = saleEnd;
								idSale = sale.getSales().getId();
								idDiscount = sale.getDiscount().getDiscount_id();
							}
						}
					}

					// Kiểm tra có idSale khác 0
					if (idSale != 0) {
						SaleDiscountId saleDiscount = new SaleDiscountId();
						saleDiscount.setDiscount(idDiscount);
						saleDiscount.setSales(idSale);

						// Lấy giá trị sale_base_price từ Sales và tính toán giảm giá
						BigDecimal salebase = BigDecimal.valueOf(sales.getSale_base_price());
						BigDecimal percent = saleDiscountService.getSaleDiscountById(saleDiscount).getDiscount()
								.getPercent();
						BigDecimal result = salebase.subtract(salebase.multiply(percent));
						// Trả về kết quả là một số nguyên
						return result.intValue();
					}
					return sales.getSale_base_price();

				} else {
					return sales.getSale_base_price();
				}
			} else {
				return -5;
			}
		} else {
			return -10;
		}
	}

}
