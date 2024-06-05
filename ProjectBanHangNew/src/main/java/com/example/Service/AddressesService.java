package com.example.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.Addresses;
import com.example.From.AddressesForm;
import com.example.Repository.IAddressesRepository;

@Service
public class AddressesService implements IAddressesService {

	@Autowired
	private IAddressesRepository service;

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
			service.save(addresses);
			return addresses;
		} catch (Exception e) {
			return null;
		}

	}

}
