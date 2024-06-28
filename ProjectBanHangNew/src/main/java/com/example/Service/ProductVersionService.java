package com.example.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Product;
import com.example.Entity.ProductVersion;
import com.example.Entity.PurchaseOderItems;
import com.example.Entity.PurchaseOrders;
import com.example.From.ProductVersionForm;
import com.example.Repository.IProductVersionRepository;

@Service
public class ProductVersionService implements IProductVersionService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IProductVersionRepository service;

	@Autowired
	private IProductService productService;

	@Autowired
	private IPurchaseOrdersService purchaseOrdersService;

	@Override
	public List<ProductVersion> getAllProductVersion() {

		return service.findAll();
	}

	@Override
	public ProductVersion getProductVersionByID(int id) {

		return service.findById(id).get();
	}

	@Override
	public void deleteProductVersion(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public ProductVersion updateProductVersion(ProductVersionForm form) {
		ProductVersion productVersion = new ProductVersion();
		productVersion.setProductVersion_id(form.getProductVersion_id());
		productVersion.setProduct(productService.getProductByID(form.getProductID()));
		productVersion.setVersion_name(form.getVersion_name());
		productVersion.setQuantity_in_stock(form.getQuantity_in_stock());
		productVersion.setUpdated_at(LocalDateTime.now());
		return service.save(productVersion);
	}

	@Override
	public ProductVersion createProductVersion(ProductVersionForm form) {

		Product product = productService.getProductByID(form.getProductID());
		ProductVersion productVersion = modelMapper.map(form, ProductVersion.class);
		productVersion.setProduct(product);
		productVersion.setIsDelete(false);
		return service.save(productVersion);

	}

	@Override
	public ProductVersion getIdItemProductVersion(int idproduct) {
		List<PurchaseOderItems> purchaseOderItems = purchaseOrdersService.checkPrepare().getPurchaseorderitem();
		for (PurchaseOderItems item : purchaseOderItems) {
			ProductVersion productVersion = item.getProductVersion();
			if (productVersion.getProduct().getProduct_id() == idproduct) {
				return productVersion;
			}
		}
		List<ProductVersion> listProductVersions=productService.getProductByID(idproduct).getProductVersion();
		for (ProductVersion productVersion : listProductVersions) {
			if (productVersion.getPurchaseOderItems().isEmpty()) {
				return productVersion;
			}
		}
		return null;
	}
}
