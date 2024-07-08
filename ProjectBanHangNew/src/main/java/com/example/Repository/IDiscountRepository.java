package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Discount;

public interface IDiscountRepository extends JpaRepository<Discount, Integer>{

}
