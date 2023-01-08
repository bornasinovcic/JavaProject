package com.example.javaproject.entities;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NutritionalValue that)) return false;
        return Objects.equals(caloriesPerProtein, that.caloriesPerProtein) && Objects.equals(caloriesPerCarbohydrate, that.caloriesPerCarbohydrate) && Objects.equals(caloriesPerFat, that.caloriesPerFat) && Objects.equals(amountOfProtein, that.amountOfProtein) && Objects.equals(amountOfCarbohydrate, that.amountOfCarbohydrate) && Objects.equals(amountOfFat, that.amountOfFat) && Objects.equals(finalCaloricValue, that.finalCaloricValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(caloriesPerProtein, caloriesPerCarbohydrate, caloriesPerFat, amountOfProtein, amountOfCarbohydrate, amountOfFat, finalCaloricValue);
    }
}
