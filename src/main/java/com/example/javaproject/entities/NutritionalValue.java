package com.example.javaproject.entities;

public class NutritionalValue {
    private Integer caloriesPerProtein = 4,
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
    public NutritionalValue() {

    }

    public Integer getCaloriesPerProtein() {
        return caloriesPerProtein;
    }

    public void setCaloriesPerProtein(Integer caloriesPerProtein) {
        this.caloriesPerProtein = caloriesPerProtein;
    }

    public Integer getCaloriesPerCarbohydrate() {
        return caloriesPerCarbohydrate;
    }

    public void setCaloriesPerCarbohydrate(Integer caloriesPerCarbohydrate) {
        this.caloriesPerCarbohydrate = caloriesPerCarbohydrate;
    }

    public Integer getCaloriesPerFat() {
        return caloriesPerFat;
    }

    public void setCaloriesPerFat(Integer caloriesPerFat) {
        this.caloriesPerFat = caloriesPerFat;
    }

    public Integer getAmountOfProtein() {
        return amountOfProtein;
    }

    public void setAmountOfProtein(Integer amountOfProtein) {
        this.amountOfProtein = amountOfProtein;
    }

    public Integer getAmountOfCarbohydrate() {
        return amountOfCarbohydrate;
    }

    public void setAmountOfCarbohydrate(Integer amountOfCarbohydrate) {
        this.amountOfCarbohydrate = amountOfCarbohydrate;
    }

    public Integer getAmountOfFat() {
        return amountOfFat;
    }

    public void setAmountOfFat(Integer amountOfFat) {
        this.amountOfFat = amountOfFat;
    }

    public Integer getFinalCaloricValue() {
        return finalCaloricValue;
    }

    public void setFinalCaloricValue(Integer finalCaloricValue) {
        this.finalCaloricValue = finalCaloricValue;
    }
}
