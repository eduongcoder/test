package com.example.Service;

import java.util.List;

import com.example.Entity.TypeOfProductGender;

public interface ITypeOfProductGenderService {
	public List<TypeOfProductGender> getAllList();
	public TypeOfProductGender getSizeByID(int id);

}
