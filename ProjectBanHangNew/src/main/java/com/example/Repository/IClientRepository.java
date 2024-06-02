package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Client;

public interface IClientRepository extends JpaRepository<Client, Integer>{

}
