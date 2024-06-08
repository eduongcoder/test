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

import com.example.Entity.Department;
import com.example.Entity.DepartmentDTO;
import com.example.Entity.Employee;
import com.example.Entity.Relative;
import com.example.Form.DepartmentForm;
import com.example.Form.EmployeeForm;
import com.example.Service.IDepartmentService;
import com.example.Service.IEmployeeService;
import com.example.Service.IRelativeService;

@RestController
@RequestMapping("/api/department")
public class DepartmentController implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://26.229.166.254:5173", "http://localhost:5173") // URL của ứng
																											// dụng
																											// React
				.allowedMethods("GET", "POST", "PUT", "DELETE").allowedHeaders("*").allowCredentials(true);
	}

	@Autowired
	private IDepartmentService service;
	@Autowired
	private IEmployeeService serviceemploy;
	@Autowired
	private IRelativeService servicerRelative;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public List<DepartmentDTO> getAllDepartment() {
		List<Department> list = service.getAllDepartments();
		List<DepartmentDTO> dtos = modelMapper.map(list, new TypeToken<List<DepartmentDTO>>() {
		}.getType());
		return dtos;
	}

	@GetMapping(value = "/{id}")
	public DepartmentDTO getDepartmentbyID(@PathVariable(name = "id") int id) {
		try {
			Department department = service.getDepartmentByID(id);
			DepartmentDTO dto = modelMapper.map(department, DepartmentDTO.class);
			return dto;
		} catch (Exception e) {
			return null;
		}

	}

	@PostMapping("/create")
	public DepartmentDTO createDepartment(@RequestBody DepartmentForm form) {
		Department department = service.createDepartment(form);

		DepartmentDTO dto = modelMapper.map(department, DepartmentDTO.class);
		return dto;
	}

	@PutMapping(value = "/update/{id}")
	public DepartmentDTO updateDepartmentById(@PathVariable(name = "id") int id, @RequestBody DepartmentForm form) {
		form.setId(id);
		service.updateDepartmentByID(form);
		DepartmentDTO dto = modelMapper.map(form, DepartmentDTO.class);
		return dto;
	}

	@DeleteMapping(value = "/delete/{id}")
	public int deleteDepartmentById(@PathVariable(name = "id") int id) {

		if (getDepartmentbyID(id) == null)
			return -1;
		else {
			service.deleteDepartmentByID(id);
			return id;
		}

	}

	@DeleteMapping(value = "deleteall/{id}")
	public int deleteEmployeeByIdall(@PathVariable(name = "id") int id) {
		try {
			service.deleteDepartmentAll(id);
			return id;

		} catch (Exception e) {
			return -1;
		}
	}
}
