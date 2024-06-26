package com.example.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Entity.PersonFix;
import com.example.From.PersonFixForm;
import com.example.Repository.IPersonFixRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class PersonFixService implements IPersonFixService{
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IPersonFixRepository service;

	 @PersistenceContext
	 private EntityManager entityManager;
	
	@Override
	public List<PersonFix> getAlList() {
		// TODO Auto-generated method stub
		return service.findAll();
	}

	@Override
	public PersonFix getPersonFixByID(int id) {
		// TODO Auto-generated method stub
		return service.findById(id).get();
	}

	@Override
    @Transactional
	public PersonFix createPersonFix(PersonFixForm form) {
		PersonFix personFix=modelMapper.map(form, PersonFix.class);
		PersonFix personFix2=service.save(personFix);
        entityManager.refresh(personFix2);

		return personFix2;
	}

	@Override
	public boolean deletePersonFix(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PersonFix updatePersonFix(PersonFix form) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
