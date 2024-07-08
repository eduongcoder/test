package com.example.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "sale_discount")
@Entity
@Data
@NoArgsConstructor
@IdClass(SaleDiscountId.class)
public class SaleDiscount {
    @Id
    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sales sales;

    @Id
    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;
    
    @Column(name = "quantity", columnDefinition = "int default 0")
    private int quantitySaleDiscount;
    
    @Column(name = "total_money", columnDefinition = "int default 0")
    private int totalMoney;
}
