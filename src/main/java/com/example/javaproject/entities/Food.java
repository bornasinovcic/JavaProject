package com.example.javaproject.entities;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Food food = (Food) o;
        return nutritionalValue.equals(food.nutritionalValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nutritionalValue);
    }

}
