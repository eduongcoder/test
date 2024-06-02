package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.TypeOfProduct;

public interface ITypeOfProductRepository extends JpaRepository<TypeOfProduct, Integer>{

}
