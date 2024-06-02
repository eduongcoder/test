package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.ClientProduct;
import com.example.Entity.ClientProductId;

public interface IClientProductRepository extends JpaRepository<ClientProduct, ClientProductId>{

}
