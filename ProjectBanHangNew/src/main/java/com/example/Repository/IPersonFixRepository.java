package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.PersonFix;

public interface IPersonFixRepository extends JpaRepository<PersonFix, Integer>{

}
