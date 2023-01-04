package com.example.javaproject.entities;

import java.math.BigDecimal;

public class Food extends Item implements CaloricValues {

    private final NutritionalValue nutritionalValue;

    public Food(String itemId, String itemName, BigDecimal itemPrice, Integer itemQuantity, NutritionalValue nutritionalValue) {
        super(itemId, itemName, itemPrice, itemQuantity);
        this.nutritionalValue = nutritionalValue;
    }
    @Override
    public Integer getCaloricValueOfAllItems() {
        return nutritionalValue.getFinalCaloricValueOfItem() * getItemQuantity();
    }
    public NutritionalValue getNutritionalValue() {
        return nutritionalValue;
    }

}
