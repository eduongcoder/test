package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Inventories;

public interface InventoryRepository extends JpaRepository<Inventories, Integer>{

}
