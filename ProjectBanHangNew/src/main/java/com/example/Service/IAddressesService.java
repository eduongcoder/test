package com.example.Service;

import java.util.List;

import com.example.Entity.Addresses;
import com.example.From.AddressesForm;

public interface IAddressesService {
	public List<Addresses> getAddresses();
	public Addresses createAddresses(AddressesForm form);
	public boolean checkAddresses(AddressesForm form);

	public Addresses getAddressByID(int id);
	public int deleteAddreess(int id);
	public Addresses updateAddresses(AddressesForm form);
}
