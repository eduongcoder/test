package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.PurchaseOrders;

public interface  IPurchaseOrdersRepositoty extends JpaRepository<PurchaseOrders, Integer>{

}
