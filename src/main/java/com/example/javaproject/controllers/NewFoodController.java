package com.example.javaproject.controllers;

import com.example.javaproject.entities.Food;
import com.example.javaproject.entities.NutritionalValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static com.example.javaproject.files.File.addNewFoodItem;
import static com.example.javaproject.files.File.getFoodItems;

public class NewFoodController {
    @FXML
    private TextField textFieldId;
    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldPrice;
    @FXML
    private TextField textFieldQuantity;
    @FXML
    private TextField textFieldProteins;
    @FXML
    private TextField textFieldCarbohydrates;
    @FXML
    private TextField textFieldFats;

    @FXML
    protected void onButtonClick() {
        String itemId = textFieldId.getText();
        String itemName = textFieldName.getText();
        String itemPriceString = textFieldPrice.getText();
        String itemQuantityString = textFieldQuantity.getText();
        String itemProteinsString = textFieldProteins.getText();
        String itemCarbohydratesString = textFieldCarbohydrates.getText();
        String itemFatsString = textFieldFats.getText();

        StringBuilder stringBuilder = new StringBuilder();

        if (itemId.isEmpty()) stringBuilder.append("You forgot to input id of food.\n");
        if (itemName.isEmpty()) stringBuilder.append("You forgot to input name of food.\n");
        if (itemPriceString.isEmpty()) stringBuilder.append("You forgot to input price of food.\n");
        if (itemQuantityString.isEmpty()) stringBuilder.append("You forgot to input quantity of food.\n");
        if (itemProteinsString.isEmpty()) stringBuilder.append("You forgot to input protein value of food.\n");
        if (itemCarbohydratesString.isEmpty()) stringBuilder.append("You forgot to input carbohydrate value of food.\n");
        if (itemFatsString.isEmpty()) stringBuilder.append("You forgot to input fat value of food.\n");

        if (stringBuilder.isEmpty()) {
            BigDecimal itemPrice = new BigDecimal(itemPriceString);
            Integer itemQuantity = Integer.valueOf(itemQuantityString);
            Integer amountOfProteins = Integer.valueOf(itemProteinsString);
            Integer amountOfCarbohydrates = Integer.valueOf(itemCarbohydratesString);
            Integer amountOfFats = Integer.valueOf(itemFatsString);
            try {
                List<Food> list = getFoodItems();
                list.add(new Food(itemId, itemName, itemPrice, itemQuantity, new NutritionalValue(amountOfProteins, amountOfCarbohydrates, amountOfFats)));
                addNewFoodItem(list);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error alert message");
            alert.setHeaderText("Please fill out empty fields");
            alert.setContentText(stringBuilder.toString());
            alert.showAndWait();
        }
    }
}
