package com.example.javaproject.entities;

import java.math.BigDecimal;
import java.util.Objects;

public class Gadget extends Item {
    private final Integer itemWarrantyInMonths;
    public Gadget(String itemId, String itemName, BigDecimal itemPrice, Integer itemQuantity, Integer itemWarrantyInMonths) {
        super(itemId, itemName, itemPrice, itemQuantity);
        this.itemWarrantyInMonths = itemWarrantyInMonths;
    }

    public Integer getItemWarrantyInMonths() {
        return itemWarrantyInMonths;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Gadget gadget = (Gadget) o;
        return Objects.equals(itemWarrantyInMonths, gadget.itemWarrantyInMonths);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), itemWarrantyInMonths);
    }
}
