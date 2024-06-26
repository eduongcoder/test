package com.example.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Entity.Color;
import com.example.Entity.Product;
import com.example.Entity.ProductVersion;
import com.example.Entity.Size;
import com.example.Entity.TypeOfProduct;
import com.example.Entity.Variant;
import com.example.From.ColorForm;
import com.example.From.ProductForm;
import com.example.From.ProductVersionForm;
import com.example.From.SizeForm;
import com.example.From.TypeOfProductForm;
import com.example.From.TypeProductVersionSizeColorVariantForm;
import com.example.From.VariantForm;
import com.example.Repository.IProductRepository;

@Service
public class ProductService implements IProductService {

	@Autowired
	private IProductRepository service;

//	@Autowired
//	private IColorService colorService;
//
//	@Autowired
//	private ISizeService sizeService;
//
//	@Autowired
//	private IVariantService variantService;
//
//	@Autowired
//	private IProductVersionService productVersionService;
//
//	@Autowired
//	private ITypeOfProductService typeOfProductService;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return service.findAll();
	}

	@Override
	public Product getProductByID(int id) {
		// TODO Auto-generated method stub
		return service.findById(id).get();
	}

	@Override
	public int deleteProduct(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Product updateProduct(ProductForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product createProduct(ProductForm form) {
		Product product = modelMapper.map(form, Product.class);

		return service.save(product);
	}

	// Chi tao duoc size va color moi cai mot thu, muon tron lai thi phai choi kieu
	// khac
	// H mot la goi lai nhieu lan, hai la tach size voi color ra goi trong api khac
	// Thiet nghi can phai co nut de dat dau moc neu phan nao trong form null thi
	// skipp qua
	// de tao dung cai can tao
	// code hien tai chi xu ly duoc van de tao moi
	// dat truong hop có sẵn rồi thì nó sẽ quằng
	// h cần thay đổi ở form để nhận thêm id của các đối tượng
	// nếu có id thì qua đây tìm đối tượng có id đó rồi gán vô thế là có câu if else
	@Transactional
	@Override
	public boolean createProductBig(TypeProductVersionSizeColorVariantForm form) {
//		try {
//			// create TypeOfProduct
//			TypeOfProduct typeOfProduct = new TypeOfProduct();
//			if (form.getTypeofproduct_id() == 0) {
//				TypeOfProductForm typeOfProductForm = new TypeOfProductForm();
//				typeOfProductForm.setTypeofproduct(form.getTypeofproductTYPE());
//				typeOfProductForm.setTypeofproductgender(form.getTypeofproductgender());
//				typeOfProductForm.setProduct(form.getProduct());
//
//				typeOfProduct = typeOfProductService.createTypeOfProduct(typeOfProductForm);
//			} else {
//				typeOfProduct = typeOfProductService.getTypeOfProductByID(form.getTypeofproduct_id());
//			}
//			Product product = new Product();
//			if (form.getProduct_id() == 0) {
//				// Create Product
//				ProductForm productForm = new ProductForm();
//				productForm.setName(form.getName());
//				productForm.setDescription(form.getDescription());
//				productForm.setState(form.getState());
//				productForm.setMaterialProduct(form.getMaterialProduct());
////				productForm.setCreated_at(LocalDateTime.now());
////				productForm.setUpdated_at(null);
////				productForm.setProductVersion(form.getProductVersion());
////				productForm.setTypeofproduct(typeOfProduct.getTypeofproduct_id());
//				product = createProduct(productForm);
//			} else {
//				product = getProductByID(form.getProduct_id());
//			}
//			ProductVersion productVersion = new ProductVersion();
//			if (form.getProductVersion_id() == 0) {
//				// Create Version
//				ProductVersionForm productVersionForm = new ProductVersionForm();
//				productVersionForm.setVersion_name(form.getVersion_name());
//				productVersionForm.setPrice(form.getPrice());
//				productVersionForm.setQuantity_in_stock(form.getQuantity_in_stock());
//				productVersionForm.setIsDelete(form.getIsDelete());
//				productVersionForm.setCreated_at(LocalDateTime.now());
//				productVersionForm.setUpdated_at(null);
//				productVersionForm.setProduct(product.getProduct_id());
//				productVersionForm.setVariants(form.getVariants());
//				productVersionForm.setPurchaseOderItems(form.getPurchaseOderItems());
//				productVersionForm.setInventories(form.getInventories());
//				productVersionForm.setOrderItems(form.getOrderItems());
//				productVersionForm.setSales(form.getSales());
//				productVersion = productVersionService.createProductVersion(productVersionForm);
//			} else {
//				productVersion = productVersionService.getProductVersionByID(form.getProductVersion_id());
//			}
//			Size size = new Size();
//			if (form.getSize_id() == 0) {
//				// Create Size
//				SizeForm sizeForm = new SizeForm();
//				sizeForm.setSize_name(form.getSize_name());
//				sizeForm.setVariants(form.getVariants());
//				size = sizeService.createSize(sizeForm);
//			} else {
//				size = sizeService.getSizeByID(form.getSize_id());
//			}
//			Color color = new Color();
//			if (form.getColor_id() == 0) {
//				// Create Color
//				ColorForm colorForm = new ColorForm();
//				colorForm.setColor_name(form.getColor_name());
//				colorForm.setVariants(form.getVariants());
//				color = colorService.createColor(colorForm);
//			} else {
//				color = colorService.getColorByID(form.getColor_id());
//			}
//			Variant variant = new Variant();
//			if (form.getVariants_id() == 0) {
//				// Create Variant
//				VariantForm variantForm = new VariantForm();
//				variantForm.setQuantity_in_stock(form.getQuantity_in_stockVariant());
//				variantForm.setColor(color.getColor_id());
//				variantForm.setSize(size.getSize_id());
//				variantForm.setImages(form.getImages());
//				variantForm.setProductversion(productVersion.getProductVersion_id());
//				variant = variantService.createVariant(variantForm);
//			} else {
//				variant = variantService.getVariantByID(form.getVariants_id());
//			}
//
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
		return false;
	}

//	@Override
//	public List<ProductShow> getAllProductShow() {
//		// TODO Auto-generated method stub
//		return service.findAll();
//	}

}
