package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Addresses;

public interface IAddressesRepository extends JpaRepository<Addresses, Integer>{

}
