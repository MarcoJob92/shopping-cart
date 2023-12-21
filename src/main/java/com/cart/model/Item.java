package com.cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {
    private String name;
    private double price;
    private int quantity;
    private boolean isOnOffer;

    public String getTotalPrice() {
        var totalPrice = (isOnOffer() ? (quantity / 2) + (quantity % 2) : quantity) * price;
        return "£" + String.format("%.2f", totalPrice);
    }

    public String getQuantityString() {
        return quantity > 1 ? " X" + quantity : "";
    }
}
