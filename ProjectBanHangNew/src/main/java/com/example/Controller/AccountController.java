package com.example.Controller;

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


import com.example.Entity.Account;
import com.example.Entity.AccountDTO;
import com.example.Entity.AddressesDTO;
import com.example.From.AccountForm;
import com.example.From.AddressesForm;
import com.example.Service.EmailService;
import com.example.Service.IAccountService;
import com.example.Service.IAddressesService;

@RequestMapping("/api/account")
@RestController
public class AccountController {
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**").allowedOrigins("http://26.110.249.245:5173", "http://localhost:5173") // URL của ứng
//																										// dụng React
//				.allowedMethods("GET", "POST", "PUT", "DELETE").allowedHeaders("*").allowCredentials(true);
//	}implements WebMvcConfigurer

	@Autowired
	private IAccountService service;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IAddressesService serviceAddress;

	
	@Autowired
	private EmailService emailService;
	
	  @PostMapping("/sendOtp")
	    public String sendOTP(@RequestParam String email) {
	        // Generate OTP (you can use any OTP generation logic here)
	        String otp = emailService.generateOTP(6); // Example OTP

	        // Send OTP via email
	        emailService.sendOTPEmail(email, "OTP Verification", otp);

	        return otp;
	    }
	
	@PostMapping("/create")
	private AccountDTO createAccount(@RequestBody AccountForm form) {
		List<Account> accounts=service.getAllAccount();
		for (Account account : accounts) {
			if (account.getEmail().equals(form.getEmail()) ) {
				form.setAccount_id(account.getAccount_id());

				 return service.updateAccountByID(form);
			}
		}
		
		return modelMapper.map(service.createEmployee(form),AccountDTO.class); 

	}

	@PostMapping("/createAddress")
	private AddressesDTO createAddressesDTO(@RequestBody AddressesForm form) {
		return modelMapper.map(serviceAddress.createAddresses(form), AddressesDTO.class);
	}

	@PostMapping("/createEmail")
	private int createAccountOnlyEmail(@RequestBody AccountForm form) {

		return service.createAccountOnlyEmail(form);

	}

	@GetMapping
	private List<AccountDTO> getall() {
		List<Account> list = service.getAllAccount();
		List<AccountDTO> dtos = modelMapper.map(list, new TypeToken<List<AccountDTO>>() {
		}.getType());
		return dtos;
	}

	@GetMapping("/checkAddress")
	private boolean checkAddress(@RequestBody AddressesForm form) {
		return serviceAddress.checkAddresses(form);
	}
	
	@GetMapping("/address")
	private List<AddressesDTO> getAllAddress() {
		List<AddressesDTO> dtos = modelMapper.map(serviceAddress.getAddresses(), new TypeToken<List<AddressesDTO>>() {
		}.getType());
		return dtos;
	}

	@PutMapping("/updateaccount")
	public AccountDTO updateAccount(@RequestBody AccountForm form) {
		if (verificationUser(form.getEmail())) {
			return service.updateAccountByID(form);
		}
		return null;
	}

	@GetMapping(value = "/getaccount/{id}")
	public AccountDTO getAccountByID(@PathVariable(name = "id") int id) {
		AccountDTO dto = modelMapper.map(service.findAccountByID(id), AccountDTO.class);
		return dto;
	}

	@GetMapping("/Verification/user")
	private boolean verificationUser(@RequestParam String email) {
		List<Account> list = service.getAllAccount();
		List<AccountDTO> dtos = modelMapper.map(list, new TypeToken<List<AccountDTO>>() {
		}.getType());
		for (AccountDTO accountDTO : dtos) {
			if (accountDTO.getEmail().equals(email) && accountDTO.getPassword() != null) {
				return true;
			} else if (accountDTO.getEmail().equals(email) && accountDTO.getPassword() == null) {
				return false;
			}

		}
		return false;
	}
	@GetMapping("/VerificationGetID/user")
	private int verificationUsergetID(@RequestParam String email) {
		List<Account> list = service.getAllAccount();
		List<AccountDTO> dtos = modelMapper.map(list, new TypeToken<List<AccountDTO>>() {
		}.getType());
		for (AccountDTO accountDTO : dtos) {
			if (accountDTO.getEmail().equals(email) && accountDTO.getPassword() != null) {
				return accountDTO.getAccount_id();
			} else if (accountDTO.getEmail().equals(email) && accountDTO.getPassword() == null) {
				return accountDTO.getAccount_id();	
			}

		}
		return 0;
	}
	@GetMapping("/Verification/pass")
	private int verificationPass(@RequestParam String email, @RequestParam String pass) {
		List<Account> list = service.getAllAccount();
		List<AccountDTO> dtos = modelMapper.map(list, new TypeToken<List<AccountDTO>>() {
		}.getType());
		for (AccountDTO accountDTO : dtos) {
			if (accountDTO.getPassword()!=null) {
				if (accountDTO.getPassword().equals(pass) && accountDTO.getEmail().equals(email)) {
					return accountDTO.getAccount_id();
				}
			}
			
		}
		return -1;
	}
}
