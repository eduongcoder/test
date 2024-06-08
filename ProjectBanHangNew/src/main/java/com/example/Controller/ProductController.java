package com.example.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.Entity.Images;
import com.example.Entity.ImagesDTO;
import com.example.Entity.Product;
import com.example.Entity.ProductDTO;
import com.example.Entity.ProductShowDTO;
import com.example.Entity.TypeOfProduct;
import com.example.Entity.TypeOfProductDTO;
import com.example.Repository.IImageRepository;
import com.example.Repository.IProductRepository;
import com.example.Repository.ITypeOfProductRepository;
import com.example.Service.IImageService;
import com.example.Service.IProductService;
import com.example.Service.ImageHandelService;


@RequestMapping("/api/product")
@RestController
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
	private ModelMapper modelMapper;


	
	List<String> conver = new ArrayList<String>();

	@GetMapping("/image")
	public List<ImagesDTO> getAll() {
		List<Images> list=service1.getAllImage();
		List<ImagesDTO> dtos=modelMapper.map(list, new TypeToken<List<ImagesDTO>>()
		{}.getType());

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

	@GetMapping
	public List<ProductDTO> getAllProduct() {
		List<Product> list = service.getAllProducts();
		List<ProductDTO> dtos = modelMapper.map(list, new TypeToken<List<ProductDTO>>() {
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

}
