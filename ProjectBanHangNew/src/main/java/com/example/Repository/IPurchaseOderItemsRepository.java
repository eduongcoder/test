package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.PurchaseOderItems;

public interface IPurchaseOderItemsRepository extends JpaRepository<PurchaseOderItems, Integer>{

}
