package com.example.javaproject.entities;

public class NutritionalValue {
    private final Integer caloriesPerProtein = 4, caloriesPerCarbohydrate = 4, caloriesPerFat = 9;
    private Integer amountOfProtein, amountOfCarbohydrate, amountOfFat, finalCaloricValue;

    public NutritionalValue(Integer amountOfProteins, Integer amountOfCarbohydrates, Integer amountOfFats) {
        this.amountOfProtein = amountOfProteins;
        this.amountOfCarbohydrate = amountOfCarbohydrates;
        this.amountOfFat = amountOfFats;
        this.finalCaloricValue = (amountOfProteins * caloriesPerProtein) +
                (amountOfCarbohydrates * caloriesPerCarbohydrate) +
                (amountOfFats * caloriesPerFat);
    }
    public NutritionalValue() {
    }

    public Integer getCaloriesPerProtein() {
        return caloriesPerProtein;
    }

    public Integer getCaloriesPerCarbohydrate() {
        return caloriesPerCarbohydrate;
    }

    public Integer getCaloriesPerFat() {
        return caloriesPerFat;
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

    public static class NutritionalValueBuilder {
        private Integer amountOfProteins;
        private Integer amountOfCarbohydrates;
        private Integer amountOfFats;

        public NutritionalValueBuilder setAmountOfProteins(Integer amountOfProteins) {
            this.amountOfProteins = amountOfProteins;
            return this;
        }

        public NutritionalValueBuilder setAmountOfCarbohydrates(Integer amountOfCarbohydrates) {
            this.amountOfCarbohydrates = amountOfCarbohydrates;
            return this;
        }

        public NutritionalValueBuilder setAmountOfFats(Integer amountOfFats) {
            this.amountOfFats = amountOfFats;
            return this;
        }

        public NutritionalValue createNutritionalValue() {
            return new NutritionalValue(amountOfProteins, amountOfCarbohydrates, amountOfFats);
        }
    }
}
