package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.SaleDiscount;
import com.example.Entity.SaleDiscountId;

public interface ISaleDiscountRepository extends JpaRepository<SaleDiscount, SaleDiscountId>{

}
