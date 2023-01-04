package com.example.javaproject.entities;

import java.math.BigDecimal;

public class Gadget extends Item {
    private final Integer itemWarrantyInMonths;
    public Gadget(String itemId, String itemName, BigDecimal itemPrice, Integer itemQuantity, Integer itemWarrantyInMonths) {
        super(itemId, itemName, itemPrice, itemQuantity);
        this.itemWarrantyInMonths = itemWarrantyInMonths;
    }

    public Integer getItemWarrantyInMonths() {
        return itemWarrantyInMonths;
    }
}
