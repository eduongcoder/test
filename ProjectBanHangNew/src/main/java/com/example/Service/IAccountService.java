package com.example.Service;

import java.util.List;

import com.example.Entity.Account;
import com.example.Entity.AccountDTO;
import com.example.From.AccountForm;

public interface IAccountService {
	public AccountDTO updateAccountByID(AccountForm form);

	public void deleteAccountByID(int id);

	public List<Account> getAllAccount();

	public Account createEmployee(AccountForm form);

	public int createAccountOnlyEmail(AccountForm form);

	public Account findAccountByID(int id);
}
