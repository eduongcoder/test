package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.ImageProduct;

public interface IImageProductRepository extends JpaRepository<ImageProduct, Integer>{

}
