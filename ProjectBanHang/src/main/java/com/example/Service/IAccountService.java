package com.example.Service;

import java.util.List;

import com.example.Entity.Account;
import com.example.From.AccountForm;

public interface IAccountService {
//	public void updateAccountByID(Ac form);

	public void deleteAccountByID(int id);

	public List<Account> getAllAccount();

	public boolean createEmployee(AccountForm form);
}
