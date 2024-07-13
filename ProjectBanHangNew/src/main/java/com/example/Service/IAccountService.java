package com.example.Service;

import java.util.List;

import com.example.Entity.Account;
import com.example.Entity.AccountDTO;
import com.example.Entity.RolePermissionDTO;
import com.example.From.AccountForm;

public interface IAccountService {
	public AccountDTO updateAccountByID(AccountForm form);

	public void deleteAccountByID(int id);

	public List<Account> getAllAccount();

	public Account createEmployee(AccountForm form);

	public Account createAccountOnlyEmail(AccountForm form);

	public Account findAccountByID(int id);
	
	public List<RolePermissionDTO> getRolePermissionDTOs(int id);
	
	public Account getAccountByEmail(String email);
	
	public Account grandRole(int idAccount, int idRole);
	
	public int updateLoginStatus(int id);
}
