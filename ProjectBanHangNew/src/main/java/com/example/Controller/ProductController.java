package com.example.Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.Entity.Category;
import com.example.Entity.CategoryDTO;
import com.example.Entity.Color;
import com.example.Entity.ColoronlyDTO;
import com.example.Entity.HistoryCategoryDTO;
import com.example.Entity.HistoryPuchaseOrder;
import com.example.Entity.HistoryPuchaseOrderDTO;
import com.example.Entity.Images;
import com.example.Entity.ImagesDTO;
import com.example.Entity.PersonFix;
import com.example.Entity.PersonFixDTO;
import com.example.Entity.Product;
import com.example.Entity.ProductDTO;
import com.example.Entity.ProductOnlyDTO;
import com.example.Entity.ProductShowDTO;
import com.example.Entity.ProductShowDTOVersion2;
import com.example.Entity.Size;
import com.example.Entity.SizeonlyDTO;
import com.example.Entity.TypeOfProductGender;
import com.example.Entity.TypeOfProductGenderDTO;
import com.example.Entity.TypeOfProductNew;
import com.example.Entity.TypeOfProductNewDTO;
import com.example.From.CategoryForm;
import com.example.From.ColorForm;
import com.example.From.HistoryCategoryForm;
import com.example.From.HistoryPuchaseOrderForm;
import com.example.From.PersonFixForm;
import com.example.From.ProductForm;

import com.example.Service.ICategoryService;
import com.example.Service.IColorService;
import com.example.Service.IHistoryCategoryService;
import com.example.Service.IHistoryPuchaseOrderService;
import com.example.Service.IImageService;
import com.example.Service.IPersonFixService;
import com.example.Service.IProductService;
import com.example.Service.ISizeService;
import com.example.Service.ITypeOfProductGenderService;
import com.example.Service.ITypeOfProductNewService;
import com.example.Service.ImageHandelService;

@RequestMapping("/api/product")
@RestController
@EnableTransactionManagement
public class ProductController implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://26.229.166.254:5173", "http://localhost:5173","http://localhost:5174") // URL của ứng
																											// dụng
																											// React
				.allowedMethods("GET", "POST", "PUT", "DELETE").allowedHeaders("*").allowCredentials(true);
	}

	@Autowired
	private IProductService service;

	@Autowired
	private IImageService service1;

	@Autowired
	private ImageHandelService service3;

	@Autowired
	private ISizeService sizeService;

	@Autowired
	private IColorService colorService;

	@Autowired
	private IPersonFixService personFixService;

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private IHistoryCategoryService historyCategoryService;

	@Autowired
	private ITypeOfProductGenderService typeOfProductGenderService;

	@Autowired
	private ITypeOfProductNewService typeOfProductNewService;

	@Autowired
	private IHistoryPuchaseOrderService historyPuchaseOrderService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/image")
	public List<ImagesDTO> getAll() {
		List<Images> list = service1.getAllImage();
		List<ImagesDTO> dtos = modelMapper.map(list, new TypeToken<List<ImagesDTO>>() {
		}.getType());

		return dtos;

	}

	@GetMapping("/productshow")
	public List<ProductShowDTO> getAllProductShow() {
		List<Product> list = service.getAllProducts();
		List<ProductShowDTO> dtos = modelMapper.map(list, new TypeToken<List<ProductShowDTO>>() {
		}.getType());

		return dtos;
	}

	// xuất ra những product với product version có variant còn hàng
	@GetMapping("/productshowV3")
	public List<ProductShowDTOVersion2> getAllProductShowV3() {
		List<Product> list = service.getAllProducts();
		
		List<Product> temp = new ArrayList<>();
		for (Product product2 : list) {
			List<Category> categories = product2.getCategories();
			Iterator<Category> iterator = categories.iterator();
			while (iterator.hasNext()) {
				Category category = iterator.next();
				if (category.isIsdelete() == true) {
					iterator.remove();
				}
			}
			product2.setCategories(categories);
			temp.add(product2);
		}
		List<ProductShowDTOVersion2> dtos = modelMapper.map(temp, new TypeToken<List<ProductShowDTOVersion2>>() {
		}.getType());

		return dtos;
	}

	// xuất ra những category chưa bị xóa, trạng thái isdelete == false
	@GetMapping("/productshowV2")
	public List<ProductShowDTO> getAllProductShowV2() {
//		List<Product> list = service.getAllProducts();

		List<Product> product = service.getAllProducts();
		List<Product> temp = new ArrayList<>();
		for (Product product2 : product) {
			List<Category> list = product2.getCategories();
			Iterator<Category> iterator = list.iterator();
			while (iterator.hasNext()) {
				Category category = iterator.next();
				if (category.isIsdelete() == true) {
					iterator.remove();
				}
			}
			product2.setCategories(list);
			temp.add(product2);
		}
		List<ProductShowDTO> dtos = modelMapper.map(temp, new TypeToken<List<ProductShowDTO>>() {
		}.getType());
		return dtos;
	}

	@GetMapping("/getproductonly")
	public List<ProductOnlyDTO> getProductOnly() {
		List<Product> list = service.getAllProducts();
		List<ProductOnlyDTO> dtos = modelMapper.map(list, new TypeToken<List<ProductOnlyDTO>>() {
		}.getType());

		return dtos;
	}

	@GetMapping
	public List<ProductDTO> getAllProduct() {
		List<Product> list = service.getAllProducts();
		List<ProductDTO> dtos = modelMapper.map(list, new TypeToken<List<ProductDTO>>() {
		}.getType());

		return dtos;
	}

	@GetMapping("/typeOnly")
	public List<TypeOfProductNewDTO> getAllTypeOnly() {
		List<TypeOfProductNew> list = typeOfProductNewService.getAllList();
		List<TypeOfProductNewDTO> dtos = modelMapper.map(list, new TypeToken<List<TypeOfProductNewDTO>>() {
		}.getType());
		return dtos;
	}

	@GetMapping("/typeGenderOnly")
	public List<TypeOfProductGenderDTO> getAllTypeGenderOnly() {
		List<TypeOfProductGender> list = typeOfProductGenderService.getAllList();
		List<TypeOfProductGenderDTO> dtos = modelMapper.map(list, new TypeToken<List<TypeOfProductGenderDTO>>() {
		}.getType());
		return dtos;
	}

	@GetMapping("/sizeOnly")
	public List<SizeonlyDTO> getAllSizeOnly() {
		List<Size> list = sizeService.getAllList();
		List<SizeonlyDTO> dtos = modelMapper.map(list, new TypeToken<List<SizeonlyDTO>>() {
		}.getType());
		return dtos;
	}

	@GetMapping("/colorOnly")
	public List<ColoronlyDTO> getAllColorOnly() {
		List<Color> list = colorService.getAllList();
		List<ColoronlyDTO> dtos = modelMapper.map(list, new TypeToken<List<ColoronlyDTO>>() {
		}.getType());
		return dtos;
	}

	@GetMapping(value = "/imagechuoi/{id}")
	public CompletableFuture<String> getImageChuoi(@PathVariable(name = "id") int id) {

		return service3.getImageBase64(id);
	}

	@GetMapping(value = "/image/{id}")
	public ImagesDTO getImageByID(@PathVariable(name = "id") int id) {

		return modelMapper.map(service1.getImageByID(id), ImagesDTO.class);
	}

	@GetMapping(value = "/{id}")
	public ProductDTO getProductByID(@PathVariable(name = "id") int id) {
		Product product = service.getProductByID(id);
		ProductDTO dto = modelMapper.map(product, ProductDTO.class);
		return dto;
	}

	@GetMapping(value = "/productshow/{id}")
	public ProductShowDTO getProductShowDTO(@PathVariable(name = "id") int id) {
		Product product = service.getProductByID(id);
		ProductShowDTO dto = modelMapper.map(product, ProductShowDTO.class);
		return dto;
	}

	// Chỉ có category không bị xóa
	@GetMapping(value = "/productshowV2/{id}")
	public ProductShowDTO getProductShowV2DTO(@PathVariable(name = "id") int id) {
		Product product = service.getProductByID(id);
		List<Category> list = product.getCategories();
		Iterator<Category> iterator = list.iterator();
		while (iterator.hasNext()) {
			Category category = iterator.next();
			if (category.isIsdelete() == true) {
				iterator.remove();
			}
		}
		product.setCategories(list);
		ProductShowDTO dto = modelMapper.map(product, ProductShowDTO.class);
		return dto;
	}

	@PostMapping("/createproduct")
	public ProductDTO creatProduct(@RequestBody ProductForm form) {
		Product product = service.createProduct(form);
		ProductDTO dto = modelMapper.map(product, ProductDTO.class);
		return dto;
	}

	@PostMapping("/createcolor")
	public int createColor(@RequestBody ColorForm form) {
		Color color = colorService.createColor(form);
		return color.getColor_id();
	}

	@PostMapping("/createproductint")
	public int creatProductInt(@RequestBody ProductForm form) {
		Product product = service.createProduct(form);
		return product.getProduct_id();
	}

	@GetMapping("/getPersonFix")
	public List<PersonFixDTO> getAllPersonFix() {
		List<PersonFix> list = personFixService.getAlList();
		List<PersonFixDTO> dtos = modelMapper.map(list, new TypeToken<List<PersonFixDTO>>() {
		}.getType());
		return dtos;
	}

	@PostMapping("/createPersonFix")
	public int getPersonFix(@RequestBody PersonFixForm form) {
		PersonFix personFix = personFixService.createPersonFix(form);
//		PersonFixDTO dtos = modelMapper.map(personFix, PersonFixDTO.class);
		return personFix.getPerson_fix_id();
	}

	@GetMapping("/getHistoryOrder")
	public List<HistoryPuchaseOrderDTO> getAllHistoryPuchaseOrder() {
		List<HistoryPuchaseOrder> list = historyPuchaseOrderService.getAlList();
		List<HistoryPuchaseOrderDTO> dtos = modelMapper.map(list, new TypeToken<List<HistoryPuchaseOrderDTO>>() {
		}.getType());
		return dtos;
	}

	@PostMapping("/createHistoryOrder")
	public int createHistoryOrder(@RequestBody HistoryPuchaseOrderForm form) {
		HistoryPuchaseOrder historyPuchaseOrder = historyPuchaseOrderService.createHistoryPuchaseOrder(form);
//		PersonFixDTO dtos = modelMapper.map(personFix, PersonFixDTO.class);
		return historyPuchaseOrder.getHistory_puchase_order_id();
	}

	@GetMapping("/getcategory")
	public List<CategoryDTO> getAllCategory() {
		List<Category> list = categoryService.getAllCategory();
		List<CategoryDTO> dtos = modelMapper.map(list, new TypeToken<List<CategoryDTO>>() {
		}.getType());
		return dtos;
	}

	@PostMapping("/createcategoryint")
	public int createCategoryInt(@RequestBody CategoryForm form) {
		return categoryService.createCategory(form).getCategory_id();
	}

	@PostMapping("/createcategory")
	public CategoryDTO createCategory(@RequestBody CategoryForm form) {
		CategoryDTO dto = modelMapper.map(categoryService.createCategory(form), CategoryDTO.class);
		return dto;
	}

	@GetMapping(value = "/getcategory/{id}")
	public List<CategoryDTO> getAllCategoryByIDProduct(@PathVariable(name = "id") int id) {
		Product product = service.getProductByID(id);
		List<Category> list = product.getCategories();
		List<CategoryDTO> dtos = modelMapper.map(list, new TypeToken<List<CategoryDTO>>() {
		}.getType());
		return dtos;
	}

	@PutMapping("/updatecategory")
	public int updateCategory(@RequestBody CategoryForm form) {
		int history = categoryService.findCategoryByID(form.getCategory_id()).getPrice_base();
		Category category = categoryService.updateCategory(form);
		HistoryCategoryForm form2 = new HistoryCategoryForm();
		if (category.getCategory_id() != 0) {
			form2.setCategory(category.getCategory_id());
			form2.setOld_price(history);
			form2.setNew_price(category.getPrice_base());
			HistoryCategoryDTO dto = modelMapper.map(historyCategoryService.createHistoryCategories(form2),
					HistoryCategoryDTO.class);
			return category.getCategory_id();
		}
		return -1;

	}

}
