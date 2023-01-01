package com.example.javaproject.entities;

public class NutritionalValue {
    private final Integer caloriesPerProtein = 4,
            caloriesPerCarbohydrate = 4,
            caloriesPerFat = 9,
            amountOfProtein,
            amountOfCarbohydrate,
            amountOfFat,
            finalCaloricValue;

    public NutritionalValue(Integer amountOfProtein, Integer amountOfCarbohydrate, Integer amountOfFat) {
        this.amountOfProtein = amountOfProtein;
        this.amountOfCarbohydrate = amountOfCarbohydrate;
        this.amountOfFat = amountOfFat;
        this.finalCaloricValue = (amountOfProtein * caloriesPerProtein) + (amountOfCarbohydrate * caloriesPerCarbohydrate) + (amountOfFat * caloriesPerFat);
    }

    public Integer getCaloriesInProtein() {
        return caloriesPerProtein;
    }

    public Integer getCaloriesInCarbohydrate() {
        return caloriesPerCarbohydrate;
    }

    public Integer getCaloriesInFat() {
        return caloriesPerFat;
    }

    public Integer getAmountOfProtein() {
        return amountOfProtein;
    }

    public Integer getAmountOfCarbohydrate() {
        return amountOfCarbohydrate;
    }

    public Integer getAmountOfFat() {
        return amountOfFat;
    }

    public Integer getFinalCaloricValueOfItem() {
        return finalCaloricValue;
    }
}
