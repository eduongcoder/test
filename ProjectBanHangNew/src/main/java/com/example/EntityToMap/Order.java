package com.example.EntityToMap;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Order {
    private int orderId;
    private int totalAmount;
    private String status;
    private String shippingAt;
    private String completeAt;
    private String createdAt;
    private String updatedAt;
    private int account;
    private String addressOrder;
    private List<OrderItem> orderItems;
}
