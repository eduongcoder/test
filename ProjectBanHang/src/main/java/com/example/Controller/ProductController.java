package com.example.Controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.Entity.ImageDTO;
import com.example.Entity.ImageProduct;
import com.example.Entity.ImageProductDTO;
import com.example.Entity.Product;
import com.example.Entity.ProductDTO;
import com.example.Entity.TypeOfProduct;
import com.example.Entity.TypeOfProductDTO;
import com.example.Repository.IImageProductRepository;
import com.example.Repository.IProductRepository;
import com.example.Repository.ITypeOfProductRepository;
import com.example.Service.IImageService;
import com.example.Service.IProductService;

@RequestMapping("/api/product")
@RestController
public class ProductController implements WebMvcConfigurer {
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://26.22.243.2:5173","http://localhost:5173") // URL của ứng dụng React
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
	@Autowired 
	private IProductService service;
	
	@Autowired 
	private IImageProductRepository service1;
	@Autowired
	private ITypeOfProductRepository service2;

	@Autowired
	private ModelMapper modelMapper;
	
	List<String> conver= new ArrayList<String>();
//	@GetMapping("/image")
//	public List<ImageDTO> getAll() {
//		List<ImageProduct> list=service1.findAll();
//		List<ImageDTO> dtos=modelMapper.map(list, new TypeToken<List<ImageDTO>>()
//		{}.getType());
//		
//		return dtos;
//	}
	@GetMapping("/typedto")
	public List<TypeOfProductDTO> getAlltypedto() {
		List<TypeOfProduct> list=service2.findAll();
		List<TypeOfProductDTO> dtos=modelMapper.map(list, new TypeToken<List<TypeOfProductDTO>>()
		{}.getType());
		return dtos;
	}

	@GetMapping
	public List<ProductDTO> getAllProduct() {
		List<Product> list= service.getAllProducts();
		List<ProductDTO> dtos=modelMapper.map(list, new TypeToken<List<ProductDTO>>()
		{}.getType());
		
		return dtos;
	}
	
	@GetMapping(value = "/{id}")
	public ProductDTO getProductByID(@PathVariable(name = "id")int id) {
		Product product=service.getProductByID(id);
		ProductDTO dto=modelMapper.map(product, ProductDTO.class);
		return dto;
	}
//	List<String> urList=new ArrayList<String>();
//	@GetMapping( "/urlimage")
//	public List<String> getUrlimage(){
//		List<ImageProduct> list=service1.findAll();
//		for (ImageProduct imageProduct : list) {
//			urList.add(convertToImageUrl(imageProduct.getImageupload()) );
//		}
//		return urList;
//	}
	 public String convertToImageUrl(byte[] base64Image) {
	        try {
	            // Giải mã chuỗi Base64 thành mảng byte
	            //byte[] imageBytes = Base64.getDecoder().decode(base64Image);
//	        	System.out.println(base64Image);
	            // Tạo URL từ mảng byte
	            String imageUrl = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(base64Image);
//	            System.out.println(imageUrl);
	            return imageUrl;
	        } catch (IllegalArgumentException e) {
	            // Xử lý nếu dữ liệu Base64 không hợp lệ
	            e.printStackTrace();
	            return null;
	        }
	    }
}
