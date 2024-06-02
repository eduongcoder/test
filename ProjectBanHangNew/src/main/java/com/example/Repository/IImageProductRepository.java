package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Images;

public interface IImageProductRepository extends JpaRepository<Images, Integer>{

}
