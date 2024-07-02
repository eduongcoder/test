package com.example.Service;

import java.util.List;

import com.example.Entity.TypeOfProductNew;

public interface ITypeOfProductNewService {
	public List<TypeOfProductNew> getAllList();
	public TypeOfProductNew getSizeByID(int id);
}
