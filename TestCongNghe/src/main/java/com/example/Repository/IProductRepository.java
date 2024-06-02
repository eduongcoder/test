package com.example.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Product;


public interface  IProductRepository extends JpaRepository<Product,Integer>{

}
