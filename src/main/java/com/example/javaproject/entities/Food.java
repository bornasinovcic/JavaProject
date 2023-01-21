package com.example.javaproject.entities;

import java.math.BigDecimal;

public class Food extends Item implements CaloricValues {

    private NutritionalValue nutritionalValue;

    public Food(String itemId, String itemName, BigDecimal itemPrice, Integer itemQuantity, NutritionalValue nutritionalValue) {
        super(itemId, itemName, itemPrice, itemQuantity);
        this.nutritionalValue = nutritionalValue;
    }
    public Food() {
    }
    @Override
    public Integer getCaloricValueOfAllItems() {
        return nutritionalValue.getFinalCaloricValue() * getItemQuantity();
    }

    public NutritionalValue getNutritionalValue() {
        return nutritionalValue;
    }

    public void setNutritionalValue(NutritionalValue nutritionalValue) {
        this.nutritionalValue = nutritionalValue;
    }
}
