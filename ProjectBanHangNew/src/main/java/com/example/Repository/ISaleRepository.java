package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Sales;

public interface ISaleRepository extends JpaRepository<Sales, Integer>{

}
