package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Orders;

public interface IOrderRepository extends JpaRepository<Orders, Integer>{
	
}
