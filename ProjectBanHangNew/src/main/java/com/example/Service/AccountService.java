package com.example.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Account;
import com.example.Entity.AccountDTO;
import com.example.Entity.Role;
import com.example.Entity.RolePermissionDTO;
import com.example.Enum.Gender;
import com.example.From.AccountForm;
import com.example.Repository.IAccountRepository;

@Service
public class AccountService implements IAccountService {

	@Autowired
	private IAccountRepository service;
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IRoleService roleService;

	@Override
	public void deleteAccountByID(int id) {

	}

	@Override
	public List<Account> getAllAccount() {
		// TODO Auto-generated method stub
		return service.findAll();
	}

	@Override
	public Account createEmployee(AccountForm form) {
		try {
			Account account = modelMapper.map(form, Account.class);
			account.setRoleID(roleService.getRoleById(form.getRoleID()));
			return service.save(account);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public Account findAccountByID(int id) {
		return service.findById(id).get();
	}

	@Override
	public AccountDTO updateAccountByID(AccountForm form) {

		Account account=findAccountByID(form.getAccount_id());
		if (form.getHeight()==null) {
			account.setHeight(Short.parseShort("0"));
			
		}else {
			account.setHeight(form.getHeight());
		}
		if (form.getWeight()==null) {
			account.setHeight(Short.parseShort("0"));
		}else {
			account.setWeight(form.getWeight());
		}
	
		account.setPassword(form.getPassword());
		account.setPhoneNumber(form.getPhoneNumber());
		account.setDayOfBirth(form.getDayOfBirth());
		String gender=form.getGender();
		if (gender!=null) {
			if (form.getGender().equals("Nữ")) {
				account.setGender(Gender.Nữ);
			}else if (form.getGender().equals("Nam")) {
				account.setGender(Gender.Nam);
			}else {
				account.setGender(Gender.Khác);
			}
		} 
		
		service.save(account);
		AccountDTO dto = modelMapper.map(account, AccountDTO.class);
		return dto;

	}

	@Override
	public Account createAccountOnlyEmail(AccountForm form) {
		try {
			Account account = modelMapper.map(form, Account.class);
			Role role=roleService.getRoleById(2);
			account.setRoleID(role);
			return service.save(account);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<RolePermissionDTO> getRolePermissionDTOs(int id) {
		List<RolePermissionDTO> list = modelMapper.map(findAccountByID(id).getRoleID().getRolePermissions(),
				new TypeToken<List<RolePermissionDTO>>() {
				}.getType());
		return list;
	}

	@Override
	public Account grandRole(int idAccount, int idRole) {
		Account account = findAccountByID(idAccount);
		account.setRoleID(roleService.getRoleById(idRole));

		return service.save(account);
	}

	@Override
	public int updateLoginStatus(int id) {
		Account account = findAccountByID(id);
		if (account.isIslogin()) {
			account.setIslogin(false);
		}else {
			account.setIslogin(true);
		}
		try {
			service.save(account);
			return id;
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public Account getAccountByEmail(String email) {
		List<Account> accounts=getAllAccount();
		for (Account account : accounts) {
			if (account.getEmail().equals(email)) {
				return account;
			}
		}
		return null;
	}

}
