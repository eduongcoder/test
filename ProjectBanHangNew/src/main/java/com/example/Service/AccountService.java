package com.example.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Account;
import com.example.Entity.AccountDTO;
import com.example.From.AccountForm;
import com.example.Repository.IAccountRepository;

@Service
public class AccountService implements IAccountService{
	
	@Autowired
	private IAccountRepository service;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public void deleteAccountByID(int id) {
		
	}

	@Override
	public List<Account> getAllAccount() {
		// TODO Auto-generated method stub
		return service.findAll();
	}

	@Override
	public boolean createEmployee(AccountForm form) {
		try {
			Account account=modelMapper.map(form,Account.class);
			service.save(account);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public Account findAccountByID(int id) {
		return service.findById(id).get();
	}

	@Override
	public AccountDTO updateAccountByID(AccountForm form) {
		AccountDTO accountDTO=modelMapper.map(findAccountByID(form.getAccounts_id()),AccountDTO.class);
		form.setAddresses(accountDTO.getAddresses());
		form.setOrders(accountDTO.getOrders());
		Account account=modelMapper.map(form, Account.class);
		
		AccountDTO dto=modelMapper.map(account, AccountDTO.class);
		return dto;
		//System.out.println(dto);
//		service.save(account);
	}

}
