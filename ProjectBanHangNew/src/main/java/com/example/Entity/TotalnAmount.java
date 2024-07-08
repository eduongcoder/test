package com.example.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TotalnAmount {
	private int totalMoney;
	private int totalQuantity;
	
    public void addAmountAndQuantity(int amount, int quantity) {
        this.totalMoney += amount;
        this.totalQuantity += quantity;
    }
}
