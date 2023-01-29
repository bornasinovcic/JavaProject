package com.example.javaproject.controllers;

import com.example.javaproject.entities.Food;
import com.example.javaproject.entities.Gadget;
import com.example.javaproject.entities.NutritionalValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.math.BigDecimal;
import java.util.List;

import static com.example.javaproject.database.DatabaseHandling.getAllFoodItems;
import static com.example.javaproject.database.DatabaseHandling.getAllGadgetItems;

public class InfoScreenController {
    @FXML
    private Label amountOfProteins;
    @FXML
    private Label amountOfCarbohydrates;
    @FXML
    private Label amountOfFats;
    @FXML
    private Label caloricAmountOfProteins;
    @FXML
    private Label caloricAmountOfCarbohydrates;
    @FXML
    private Label caloricAmountOfFats;
    @FXML
    private Label totalCaloricValue;
    @FXML
    private Label totalFoodCost;


    public void initialize() {
        List<Food> foodlist = getAllFoodItems();
        List<Gadget> gadgetlist = getAllGadgetItems();

        Integer sumOfProteins = foodlist
                .stream()
                .mapToInt(value -> value.getItemQuantity() * value.getNutritionalValue().getAmountOfProtein())
                .sum();
        Integer sumOfCarbohydrates = foodlist
                .stream()
                .mapToInt(value -> value.getItemQuantity() * value.getNutritionalValue().getAmountOfCarbohydrate())
                .sum();
        Integer sumOfFats = foodlist
                .stream()
                .mapToInt(value -> value.getItemQuantity() * value.getNutritionalValue().getAmountOfFat())
                .sum();


        NutritionalValue nutritionalValue = new NutritionalValue();
        amountOfProteins.setText(String.valueOf(sumOfProteins));
        amountOfCarbohydrates.setText(String.valueOf(sumOfCarbohydrates));
        amountOfFats.setText(String.valueOf(sumOfFats));

        caloricAmountOfProteins.setText(String.valueOf(sumOfProteins * nutritionalValue.getCaloriesPerProtein()));
        caloricAmountOfCarbohydrates.setText(String.valueOf(sumOfCarbohydrates * nutritionalValue.getCaloriesPerCarbohydrate()));
        caloricAmountOfFats.setText(String.valueOf(sumOfFats * nutritionalValue.getCaloriesPerFat()));


        Integer totalCalories = foodlist
                .stream()
                .mapToInt(Food::getCaloricValueOfAllItems)
                .sum();
        totalCaloricValue.setText(totalCalories + " kcal");
        BigDecimal bigDecimal = BigDecimal.ZERO;
        for (Food food : foodlist)
            bigDecimal = bigDecimal.add(food.getItemPrice().multiply(BigDecimal.valueOf(food.getItemQuantity())));

        totalFoodCost.setText(bigDecimal + " €");
    }
}
