package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.ProductVersion;

public interface IProductVersionRepository extends JpaRepository<ProductVersion, Integer> {

}
