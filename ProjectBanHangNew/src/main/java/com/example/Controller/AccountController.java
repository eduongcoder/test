package com.example.Controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Entity.Account;
import com.example.Entity.AccountDTO;
import com.example.Entity.Addresses;
import com.example.Entity.AddressesDTO;
import com.example.Entity.PermissionsDTO;
import com.example.Entity.Role;
import com.example.Entity.RoleDTO;
import com.example.Entity.RolePermission;
import com.example.Entity.RolePermissionDTO;
import com.example.Entity.RolePermissionListDTO;
import com.example.From.AccountForm;
import com.example.From.AddressesForm;
import com.example.From.RoleForm;
import com.example.From.RolePermissionForm;
import com.example.Repository.IAccountRepository;
import com.example.Service.EmailService;
import com.example.Service.IAccountService;
import com.example.Service.IAddressesService;
import com.example.Service.IPermissionsService;
import com.example.Service.IRolePermissionService;
import com.example.Service.IRoleService;

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
	private IAccountRepository accountRepository;

	@Autowired
	private IAddressesService serviceAddress;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IPermissionsService permissionsService;

	@Autowired
	private IRolePermissionService rolePermissionService;

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
		List<Account> accounts = service.getAllAccount();

		for (Account account : accounts) {
			if (account.getEmail().equals(form.getEmail())&& account.getPassword()==null) {
				form.setAccount_id(account.getAccount_id());

				return service.updateAccountByID(form);
			}	
		}
		
		return modelMapper.map(service.createEmployee(form), AccountDTO.class);
	}

	@PostMapping("/createAddress")
	private AddressesDTO createAddressesDTO(@RequestBody AddressesForm form) {
		return modelMapper.map(serviceAddress.createAddresses(form), AddressesDTO.class);
	}

	@PostMapping("/checkEmail")
	private int createAccountOnlyEmail(@RequestBody AccountForm form) {
		Account account0= service.getAccountByEmail(form.getEmail());
		if (account0!=null) {
			return account0.getAccount_id();
		}
		Account account= service.createAccountOnlyEmail(form);
		return account.getAccount_id();

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
	
	@GetMapping("/checkAddressNotNull")
	private boolean checkAddressNotNull(@RequestParam int idAccount) {
		Account account=service.findAccountByID(idAccount);
		if (account.getAddresses().isEmpty()) {
			return false;
		}
		return true;
	}

	@PutMapping("/updateAddress")
	private boolean updateAddress(@RequestBody AddressesForm form) {
		Addresses addresses = serviceAddress.updateAddresses(form);
		if (addresses != null) {
			return true;
		} else {
			return false;
		}
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

	@PutMapping("/updateaccountNoVerifi")
	public boolean updateaccountNoVerifi(@RequestBody AccountForm form) {
		AccountDTO account = service.updateAccountByID(form);
		if (account != null) {
			return true;
		}
		return false;

	}

	@PutMapping("/uploadAvatar")
	public int uploadAvatar(@RequestParam("file") MultipartFile file, @RequestParam int idAccount) {
		try {
			Account account = service.findAccountByID(idAccount);
			account.setAvatar(file.getBytes());
			return accountRepository.save(account).getAccount_id();
		} catch (IOException e) {
			return -1;
		}
	}

	@GetMapping(value = "/getaccount/{id}")
	public AccountDTO getAccountByID(@PathVariable(name = "id") int id) {
		AccountDTO dto = modelMapper.map(service.findAccountByID(id), AccountDTO.class);
		return dto;
	}

	@GetMapping("/getRole")
	public List<RoleDTO> getAllRole() {
		List<RoleDTO> dtos = modelMapper.map(roleService.getAll(), new TypeToken<List<RoleDTO>>() {
		}.getType());
		return dtos;
	}

	@GetMapping("/getPermisson")
	public List<PermissionsDTO> getAllPermisson() {
		List<PermissionsDTO> dtos = modelMapper.map(permissionsService.getAll(), new TypeToken<List<PermissionsDTO>>() {
		}.getType());
		return dtos;
	}

	@PutMapping("/updateRoleAccount/{id}")
	public int updateRoleAccount(@PathVariable(name = "id") int id, @RequestParam int idRole) {
		return service.grandRole(id, idRole).getAccount_id();
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
		return -1;
	}

	@GetMapping("/Verification/pass")
	private int verificationPass(@RequestParam String email, @RequestParam String pass) {
		List<Account> list = service.getAllAccount();
		List<AccountDTO> dtos = modelMapper.map(list, new TypeToken<List<AccountDTO>>() {
		}.getType());
		for (AccountDTO accountDTO : dtos) {
			if (accountDTO.getPassword() != null) {
				if (accountDTO.getPassword().equals(pass) && accountDTO.getEmail().equals(email)) {
					return accountDTO.getAccount_id();
				}
			}
		}
		return -1;
	}

	@PostMapping("/grandRolePermission")
	private RolePermissionDTO grandRolePermission(@RequestBody RolePermissionForm form) {

		return modelMapper.map(rolePermissionService.createRolePermission(form), RolePermissionDTO.class);
	}

	@DeleteMapping("/revokeRolePermission")
	private boolean revokeRolePermission(@RequestParam int idRole, @RequestParam int idPermission) {

		return rolePermissionService.deletePermission(idRole, idPermission);
	}

	@GetMapping("/checkRoleName")
	private int checkRoleName(@RequestParam String roleName) {
		List<Role> list = roleService.getAll();
		for (Role role : list) {
			if (role.getRole().equals(roleName)) {
				return role.getRole_id();
			}
		}
		return -1;
	}

	@GetMapping(value = "/checkPermission/{id}")
	private AccountDTO checkPermission(@PathVariable(name = "id") int id) {
		List<RolePermission> role = service.findAccountByID(id).getRoleID().getRolePermissions();
		for (RolePermission rolePermission : role) {
			if (rolePermission.getPermission().getPermission_name().equals("Đăng nhập trang admin")) {
				if (service.updateLoginStatus(id) == id) {
					Account account = service.findAccountByID(id);
					return modelMapper.map(account, AccountDTO.class);
				}
			}
		}
		return null;
	}

	@GetMapping(value = "/checkPermissionAll/{id}")
	private boolean checkPermissionAll(@PathVariable(name = "id") int id, @RequestParam int idPermisson) {
		List<RolePermission> role = service.findAccountByID(id).getRoleID().getRolePermissions();
		for (RolePermission rolePermission : role) {
			if (rolePermission.getPermission().getPermissions_id() == idPermisson) {
				Account account = service.findAccountByID(id);
				return true;
			}
		}
		return false;
	}

	@PutMapping(value = "/updateLoginStatus/{id}")
	private int updateLoginStatus(@PathVariable(name = "id") int id) {
		return service.updateLoginStatus(id);
	}
	
	@PostMapping("/createRole")
	private int createRole(@RequestBody RoleForm form) {
		return roleService.createRole(form).getRole_id();
	}

	@PutMapping("/updateRole")
	private int updateRole(@RequestBody RoleForm form) {
		return roleService.updateRole(form).getRole_id();
	}

	// truyền các quyền vào trong role đó, các quyền có sẵn nhưng ko có trong list
	// thì sẽ bị xóa
	@PostMapping(value = "/grandManyPermission/{id}")
	public int postMethodName(@PathVariable(name = "id") int id, @RequestBody RolePermissionListDTO list) {
		List<RolePermission> role = roleService.getRoleById(id).getRolePermissions();
		Set<Integer> permissionIds = new HashSet<>(list.getIdRolePermission());

		// Lấy các permission ID hiện tại của role
		Set<Integer> existingPermissionIds = role.stream()
				.map(rolePermission -> rolePermission.getPermission().getPermissions_id()).collect(Collectors.toSet());

		for (Integer permissionId : permissionIds) {
			if (!existingPermissionIds.contains(permissionId)) {
				RolePermissionForm rolePermissionForm = new RolePermissionForm();
				rolePermissionForm.setPermission(permissionId);
				rolePermissionForm.setRole(id);
				rolePermissionService.createRolePermission(rolePermissionForm);
			}
		}

		for (RolePermission rolePermission : role) {
			if (!permissionIds.contains(rolePermission.getPermission().getPermissions_id())) {
				rolePermissionService.deletePermission(rolePermission.getRole().getRole_id(),
						rolePermission.getPermission().getPermissions_id());
			}
		}

		return id;
	}

}
