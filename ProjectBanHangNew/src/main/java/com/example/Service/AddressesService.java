package com.example.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Account;
import com.example.Entity.Addresses;
import com.example.From.AddressesForm;
import com.example.Repository.IAddressesRepository;

@Service
public class AddressesService implements IAddressesService {

	@Autowired
	private IAddressesRepository service;
	@Autowired
	private IAccountService accountService;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<Addresses> getAddresses() {
		// TODO Auto-generated method stub
		return service.findAll();
	}

	@Override
	public Addresses getAddressByID(int id) {
		// TODO Auto-generated method stub
		return service.findById(id).get();
	}

	@Override
	public int deleteAddreess(int id) {
		try {
			service.deleteById(id);
			return id;
		} catch (Exception e) {
			return -1;
		}

	}

	@Override
	public Addresses updateAddresses(AddressesForm form) {

		try {
			Addresses addresses = modelMapper.map(form, Addresses.class);
			Account account=accountService.findAccountByID(form.getAccount_id());
			addresses.setAccount_id(account);
			addresses.setUpdated_at(LocalDateTime.now());
			return service.save(addresses);
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public Addresses createAddresses(AddressesForm form) {
		try {
			Addresses addresses=modelMapper.map(form, Addresses.class);
			
			return service.save(addresses);
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public boolean checkAddresses(AddressesForm form) {
		List<Addresses> list=getAddresses();
		for (Addresses addresses : list) {
			if (addresses.getCity().equals(form.getCity())&& 
				addresses.getState().equals(form.getState()) && addresses.getCountry().equals(form.getCountry()) && addresses.getAccount_id().getAccount_id()==form.getAccount_id()) {
				return true;
			}
		}
		return false;
	}

}
