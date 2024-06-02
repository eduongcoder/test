package com.example.Controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.Entity.Employee;
import com.example.Entity.EmployeeDTO;
import com.example.Entity.Relative;
import com.example.Form.EmployeeForm;
import com.example.Service.IEmployeeService;
import com.example.Service.IRelativeService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/api/employee")
@RestController
public class EmployeeControl implements WebMvcConfigurer {
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://26.22.243.2:5173","http://localhost:5173") // URL của ứng dụng React
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

	@Autowired
	private IEmployeeService Service;
	@Autowired
	private IRelativeService servicerRelative;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "/{id}")
	public EmployeeDTO getEmployeebyID(@PathVariable(name = "id") int id) {
		Employee employee = Service.getEmployeeByID(id);

		EmployeeDTO dto = modelMapper.map(employee, EmployeeDTO.class);
		return dto;
	}

	@GetMapping
	public List<EmployeeDTO> getAllRelative() {
		List<Employee> list = Service.getAllEmployees();
		List<EmployeeDTO> dtos = modelMapper.map(list, new TypeToken<List<EmployeeDTO>>() {
		}.getType());
		return dtos;
	}

	@PostMapping("/create")
	public EmployeeDTO createEmployee(@RequestBody EmployeeForm form) {
		Employee employee= Service.createEmployee(form);
		EmployeeDTO dto = modelMapper.map(employee, EmployeeDTO.class);
		return dto;
	}

	@PutMapping(value = "/update/{id}")
	public EmployeeDTO updateEmployeeById(@PathVariable(name = "id") int id, @RequestBody EmployeeForm form) {
		form.setId(id);
		Service.updateEmployeeByID(form);
		EmployeeDTO dto = modelMapper.map(form, EmployeeDTO.class);
		return dto;
	}

	@DeleteMapping(value = "/delete/{id}")
	public int deleteEmployeeById(@PathVariable(name = "id") int id) {
		
		if(getEmployeebyID(id)==null) {
			return -1;
		}
		else {
			Service.deleteEmployeeByID(id);
			return id;
		}
		
	}

//	@DeleteMapping(value = "/deleteall/{id}")
//	public void deleteEmployeeByIdAll(@PathVariable(name = "id") int id) {
//		List<Relative> relatives = servicerRelative.getAllRelatives();
//		for (Relative relative : relatives) {
//			if (relative.getEmployeeid().getId() == id) {
//				servicerRelative.deleteRelativeByID(relative.getId());
//			}	
//		}
//		Service.deleteEmployeeByID(id);
//	}

}
