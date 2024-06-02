package com.example.Controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.Entity.AccountDTO;
import com.example.Entity.ClientProduct;
import com.example.Entity.ClientProductDTO;
import com.example.Entity.ClientProductId;
import com.example.Entity.Product;
import com.example.From.ClientProductForm;
import com.example.Service.IClientProductService;
import com.example.Service.IProductService;

@RequestMapping("/api/clientproduct")
@RestController
public class ClientProductController implements WebMvcConfigurer {
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://26.22.243.2:5173","http://localhost:5173") // URL của ứng dụng React
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
	
	@Autowired
	private IClientProductService service;
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired 
	private IProductService serviceProduct;
	@GetMapping
	private List<ClientProductDTO> getall(){
		List<ClientProduct> list=service.getAllClientProduct();
		List<ClientProductDTO> dtos=modelMapper.map(list, new TypeToken<List<ClientProductDTO>>()
		{}.getType());
		
		return dtos;
	}
	
	@GetMapping("/productclient")
	private List<ClientProductDTO> getProducts(@RequestParam int productid){
		List<ClientProductDTO> dtos=modelMapper.map(service.getClientProductByID(productid), new TypeToken<List<ClientProductDTO>>()
		{}.getType());
		System.out.println(dtos);
		return dtos;
	}
	@PostMapping("/create")
	private boolean createClientProduct(@RequestBody ClientProductForm form ) {
		if(service.createClientProduct(form)!=null)
			return true;
		return false;
	}
	@DeleteMapping("/delete")
	private boolean deleteClientProduct(@RequestBody ClientProductId id) {
		if (service.deleteClientProduct(id)!=null) {
			return true;
		}
		return false;
	}
}
