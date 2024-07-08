package com.example.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Account;
import com.example.Entity.AccountDTO;
import com.example.Entity.RolePermission;
import com.example.Entity.RolePermissionDTO;
import com.example.Entity.SalesDTO;
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
		AccountDTO accountDTO = modelMapper.map(findAccountByID(form.getAccount_id()), AccountDTO.class);

		form.setAddresses(accountDTO.getAddresses());
		form.setOrders(accountDTO.getOrders());
		form.setCreated_at(accountDTO.getCreated_at());
		form.setUpdated_at(LocalDateTime.now());
		Account account = modelMapper.map(form, Account.class);
		service.save(account);
		AccountDTO dto = modelMapper.map(account, AccountDTO.class);
		return dto;

	}

	@Override
	public int createAccountOnlyEmail(AccountForm form) {
		try {
			Account account = modelMapper.map(form, Account.class);

			return service.save(account).getAccount_id();
		} catch (Exception e) {
			return -1;
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
		account.setIslogin(true);
		try {
			service.save(account);
			return id;
		} catch (Exception e) {
			return -1;
		}
	}

}
