package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Variant;

public interface  IVariantRepository extends JpaRepository<Variant, Integer>{

}
