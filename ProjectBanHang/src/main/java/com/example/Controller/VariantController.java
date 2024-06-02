package com.example.Controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.Entity.TypeOfProductDTO;
import com.example.Entity.Variant;
import com.example.Entity.VariantDTO;
import com.example.Service.IVariantService;

@RequestMapping("/api/variant")
@RestController
public class VariantController implements WebMvcConfigurer {
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://26.22.243.2:5173","http://localhost:5173") // URL của ứng dụng React
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
	@Autowired
	private IVariantService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	private List<VariantDTO> getallVariant(){
		List<Variant> list=service.getAllVariants();
		List<VariantDTO> dtos=modelMapper.map(list, new TypeToken<List<VariantDTO>>()
		{}.getType());
		return dtos;
	}
}
