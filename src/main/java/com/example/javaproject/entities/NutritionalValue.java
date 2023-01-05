package com.example.javaproject.entities;

public class NutritionalValue {
    private final Integer caloriesPerProtein = 4,
            caloriesPerCarbohydrate = 4,
            caloriesPerFat = 9,
            amountOfProtein,
            amountOfCarbohydrate,
            amountOfFat,
            finalCaloricValue;

    public NutritionalValue(Integer amountOfProteins, Integer amountOfCarbohydrates, Integer amountOfFats) {
        this.amountOfProtein = amountOfProteins;
        this.amountOfCarbohydrate = amountOfCarbohydrates;
        this.amountOfFat = amountOfFats;
        this.finalCaloricValue = (amountOfProteins * caloriesPerProtein) + (amountOfCarbohydrates * caloriesPerCarbohydrate) + (amountOfFats * caloriesPerFat);
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
