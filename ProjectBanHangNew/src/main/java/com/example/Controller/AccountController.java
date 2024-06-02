package com.example.Controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.Entity.Account;
import com.example.Entity.AccountDTO;
import com.example.Entity.TypeOfProductDTO;
import com.example.From.AccountForm;
import com.example.Repository.IAccountRepository;
import com.example.Repository.IClientRepository;
import com.example.Service.IAccountService;

@RequestMapping("/api/account")
@RestController
public class AccountController implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://26.22.243.2:5173", "http://localhost:5173") // URL của ứng
																										// dụng React
				.allowedMethods("GET", "POST", "PUT", "DELETE").allowedHeaders("*").allowCredentials(true);
	}

	@Autowired
	private IAccountService service;
	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/create")
	private boolean createAccount(@RequestBody AccountForm form) {
		try {
			return service.createEmployee(form);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;

	}

	@GetMapping
	private List<AccountDTO> getall() {
		List<Account> list = service.getAllAccount();
		List<AccountDTO> dtos = modelMapper.map(list, new TypeToken<List<AccountDTO>>() {
		}.getType());
		return dtos;
	}

	@PutMapping("/updateaccount")
	public AccountDTO updateAccount(@RequestBody AccountForm form) {
		return service.updateAccountByID(form);
	}
	
	@GetMapping(value = "/getaccount/{id}")
	public AccountDTO getAccountByID(@PathVariable(name = "id")int id) {
		AccountDTO  dto=modelMapper.map(service.findAccountByID(id), AccountDTO.class);
		return dto;
	}
	
	@GetMapping("/Verification/user")
	private boolean verificationUser(@RequestParam String email) {
		List<Account> list = service.getAllAccount();
		List<AccountDTO> dtos = modelMapper.map(list, new TypeToken<List<AccountDTO>>() {
		}.getType());
		for (AccountDTO accountDTO : dtos) {
			if (accountDTO.getEmail().equals(email)) {
				return true;

			}
		}
		return false;
	}

	@GetMapping("/Verification/pass")
	private int verificationPass(@RequestParam String email, @RequestParam String pass) {
		List<Account> list = service.getAllAccount();
		List<AccountDTO> dtos = modelMapper.map(list, new TypeToken<List<AccountDTO>>() {
		}.getType());
		for (AccountDTO accountDTO : dtos) {

			if (accountDTO.getPassword().equals(pass) && accountDTO.getEmail().equals(email)) {
				return accountDTO.getAccounts_id();
			}

		}
		return -1;
	}
}
