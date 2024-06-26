package com.example.Service;

import java.util.List;

import com.example.Entity.PersonFix;
import com.example.From.PersonFixForm;

public interface IPersonFixService {
	public List<PersonFix> getAlList();
	public PersonFix getPersonFixByID(int id);
	public PersonFix createPersonFix(PersonFixForm form);
	public boolean deletePersonFix(int id);
	public PersonFix updatePersonFix(PersonFix form);
}
