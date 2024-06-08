package com.example.Controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import com.example.Entity.Relative;
import com.example.Entity.RelativeDTO;
import com.example.Form.RelativeFrom;
import com.example.Service.IRelativeService;

@RequestMapping("/api/relative")
@RestController
public class RelativeController implements WebMvcConfigurer{
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://26.229.166.254:5173","http://localhost:5173") // URL của ứng dụng React
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
	@Autowired
	private IRelativeService Service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping(value = "/{id}")
	public RelativeDTO getRelativebyID(@PathVariable(name = "id") int id) {
		Relative relative = Service.getRelativeByID(id);
		RelativeDTO dto = modelMapper.map(relative, RelativeDTO.class);
		return dto;
	}

	@GetMapping
	public List<RelativeDTO> getAllRelative() {
		List<Relative> list = Service.getAllRelatives();
		List<RelativeDTO> dtos = modelMapper.map(list, new TypeToken<List<RelativeDTO>>() {
		}.getType());
		return dtos;
	}

//	@GetMapping
//	public List<Relative> getAllRelative1() {
//		List<Relative> list = Service.getAllRelatives();
//		List<RelativeDTO> dtos = modelMapper.map(list, new TypeToken<List<RelativeDTO>>() {
//		}.getType());
//		return list;
//	}
	@PostMapping("/create")
	public RelativeDTO createRelative(@RequestBody RelativeFrom form) {
		Relative relative= Service.createRelative(form);
		RelativeDTO dto=modelMapper.map(relative, RelativeDTO.class) ;
		return dto;
	}
	@PutMapping(value = "/update/{id}")
	public RelativeDTO updateRelativeById(@PathVariable(name = "id") int id, @RequestBody RelativeFrom form) {
		form.setId(id);
		System.out.println(form);
		Service.updateRelativeByID(form);
		RelativeDTO dto=modelMapper.map(form, RelativeDTO.class) ;
		return dto;
	}
	@DeleteMapping(value = "/delete/{id}")
	public int deleteRelativeById(@PathVariable(name = "id") int id) {
		if(getRelativebyID(id)==null) {
			return -1;
		}
		else {
			Service.deleteRelativeByID(id);
			return id;
		}
	}
}
