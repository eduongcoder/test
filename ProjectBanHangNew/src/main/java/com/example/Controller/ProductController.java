package com.example.Controller;

import java.util.ArrayList;
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
import com.example.Entity.Images;
import com.example.Entity.ImagesDTO;
import com.example.Entity.PersonFix;
import com.example.Entity.PersonFixDTO;
import com.example.Entity.Product;
import com.example.Entity.ProductDTO;
import com.example.Entity.ProductOnlyDTO;
import com.example.Entity.ProductShowDTO;
import com.example.Entity.Size;
import com.example.Entity.SizeonlyDTO;
import com.example.Entity.TypeOfProduct;
import com.example.Entity.TypeOfProductDTO;
import com.example.Entity.TypeOfProductonlyDTO;
import com.example.From.CategoryForm;
import com.example.From.HistoryCategoryForm;
import com.example.From.PersonFixForm;
import com.example.From.ProductForm;
import com.example.From.TypeProductVersionSizeColorVariantForm;
import com.example.Repository.IImageRepository;
import com.example.Repository.IProductRepository;
import com.example.Repository.ITypeOfProductRepository;
import com.example.Service.ICategoryService;
import com.example.Service.IColorService;
import com.example.Service.IHistoryCategoryService;
import com.example.Service.IImageService;
import com.example.Service.IPersonFixService;
import com.example.Service.IProductService;
import com.example.Service.ISizeService;
import com.example.Service.ITypeOfProductService;
import com.example.Service.ImageHandelService;

import jakarta.transaction.Transactional;

@RequestMapping("/api/product")
@RestController
@EnableTransactionManagement
public class ProductController implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://26.229.166.254:5173", "http://localhost:5173") // URL của ứng
																											// dụng
																											// React
				.allowedMethods("GET", "POST", "PUT", "DELETE").allowedHeaders("*").allowCredentials(true);
	}

	@Autowired
	private IProductService service;

	@Autowired
	private IImageService service1;
	@Autowired
	private ITypeOfProductRepository service2;

	@Autowired
	private ImageHandelService service3;

	@Autowired
	private ITypeOfProductService service4;

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
	private ModelMapper modelMapper;

	List<String> conver = new ArrayList<String>();

	@GetMapping("/image")
	public List<ImagesDTO> getAll() {
		List<Images> list = service1.getAllImage();
		List<ImagesDTO> dtos = modelMapper.map(list, new TypeToken<List<ImagesDTO>>() {
		}.getType());

		return dtos;

	}

	@GetMapping("/typedto")
	public List<TypeOfProductDTO> getAlltypedto() {
		List<TypeOfProduct> list = service2.findAll();
		List<TypeOfProductDTO> dtos = modelMapper.map(list, new TypeToken<List<TypeOfProductDTO>>() {
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
	
	@GetMapping("/getproductonly")
	public List<ProductOnlyDTO> getProductOnly(){
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

	@GetMapping("/typeofproduct")
	public List<TypeOfProductDTO> getAllTypeOfProduct() {
		List<TypeOfProduct> list = service4.getAllTypeOfProduct();
		List<TypeOfProductDTO> dtos = modelMapper.map(list, new TypeToken<List<TypeOfProductDTO>>() {
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

	@GetMapping("/typeofproductOnly")
	public List<TypeOfProductonlyDTO> getAllTypeOfProductOnly() {
		List<TypeOfProduct> list = service4.getAllTypeOfProduct();
		List<TypeOfProductonlyDTO> dtos = modelMapper.map(list, new TypeToken<List<TypeOfProductonlyDTO>>() {
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

	@PostMapping("/createproduct")
	public ProductDTO creatProduct(@RequestBody ProductForm form) {
		Product product=service.createProduct(form);
		ProductDTO dto = modelMapper.map(product, ProductDTO.class);
		return dto;
	}
	
	@PostMapping("/createproductint")
	public int creatProductInt(@RequestBody ProductForm form) {
		Product product=service.createProduct(form);
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
	public PersonFixDTO getPersonFix(@RequestBody PersonFixForm form) {
		PersonFix personFix=personFixService.createPersonFix(form);
		PersonFixDTO dtos = modelMapper.map(personFix, PersonFixDTO.class);
		return dtos;
	}
	
	@GetMapping("/getcategory")
	public List<CategoryDTO> getAllCategory(){
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
		CategoryDTO dto=modelMapper.map(categoryService.createCategory(form), CategoryDTO.class);
		return dto;
	}
	
	@GetMapping(value = "/getcategory/{id}")
	public List<CategoryDTO> getAllCategoryByIDProduct(@PathVariable(name = "id") int id){
		Product product=service.getProductByID(id);
		List<Category> list = product.getCategories();
		List<CategoryDTO> dtos = modelMapper.map(list, new TypeToken<List<CategoryDTO>>() {
		}.getType());
		return dtos;
	} 
	@PutMapping("/updatecategory")
	public HistoryCategoryDTO updateCategory(@RequestBody CategoryForm form) {
		int history=categoryService.findCategoryByID(form.getCategory_id()).getPrice_base();
		Category category=categoryService.updateCategory(form);
		HistoryCategoryForm form2=new HistoryCategoryForm();
		if (category.getCategory_id()!=0) {
			form2.setCategory(category.getCategory_id());
			form2.setOld_price(history);
			form2.setNew_price(category.getPrice_base());
			HistoryCategoryDTO dto =modelMapper.map(historyCategoryService.createHistoryCategories(form2), HistoryCategoryDTO.class) ; 
			return dto;
		}
		return null;
		
	}

}
