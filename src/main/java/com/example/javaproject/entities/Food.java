package com.example.javaproject.entities;

import java.math.BigDecimal;

public class Food extends Item implements CaloricValues {
    private NutritionalValue nutritionalValue;

    public Food(String itemId, String itemName, BigDecimal itemPrice, Integer itemQuantity, NutritionalValue nutritionalValue) {
        super(itemId, itemName, itemPrice, itemQuantity);
        this.nutritionalValue = nutritionalValue;
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

    public static class FoodBuilder {
        private String itemId;
        private String itemName;
        private BigDecimal itemPrice;
        private Integer itemQuantity;
        private NutritionalValue nutritionalValue;

        public FoodBuilder setItemId(String itemId) {
            this.itemId = itemId;
            return this;
        }

        public FoodBuilder setItemName(String itemName) {
            this.itemName = itemName;
            return this;
        }

        public FoodBuilder setItemPrice(BigDecimal itemPrice) {
            this.itemPrice = itemPrice;
            return this;
        }

        public FoodBuilder setItemQuantity(Integer itemQuantity) {
            this.itemQuantity = itemQuantity;
            return this;
        }

        public FoodBuilder setNutritionalValue(NutritionalValue nutritionalValue) {
            this.nutritionalValue = nutritionalValue;
            return this;
        }

        public Food createFood() {
            return new Food(itemId, itemName, itemPrice, itemQuantity, nutritionalValue);
        }
    }

    @Override
    public String toString() {
        return getItemId() + ";" +
                getItemName() + ";" +
                getItemPrice() + ";" +
                getItemQuantity() + ";" +
                getNutritionalValue().getAmountOfProtein() + ";" +
                getNutritionalValue().getAmountOfCarbohydrate() + ";" +
                getNutritionalValue().getAmountOfFat();
    }
}
