package com.example.EntityToMap;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItem {
    private int orderItemsId;
    private String productName;
    private int productPrice;
    private int priceBase;
    private int quantity;
    private String createdAt;
    private String updatedAt;
    private int productId;
    private int orderId;
    private String size;
    private String color;
    private int sizeId;
    private int colorId;
}
