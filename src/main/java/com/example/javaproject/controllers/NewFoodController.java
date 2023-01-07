package com.example.javaproject.controllers;

import com.example.javaproject.entities.Food;
import com.example.javaproject.entities.NutritionalValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

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

        if (itemId.isEmpty()) stringBuilder.append("You forgot to input id of that food.\n");
        if (itemName.isEmpty()) stringBuilder.append("You forgot to input name of that food.\n");
        if (itemPriceString.isEmpty()) stringBuilder.append("You forgot to input price of that food.\n");
        if (itemQuantityString.isEmpty()) stringBuilder.append("You forgot to input quantity of that food.\n");
        if (itemProteinsString.isEmpty()) stringBuilder.append("You forgot to input protein value of that food.\n");
        if (itemCarbohydratesString.isEmpty()) stringBuilder.append("You forgot to input carbohydrate value of that food.\n");
        if (itemFatsString.isEmpty()) stringBuilder.append("You forgot to input fat value of that food.\n");

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
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Correct input of data for food object.");
                alert.setTitle("New food added.");
                alert.setContentText(
                        "itemId -> " + itemId +
                        "\nitemName -> " + itemName +
                        "\nitemPrice -> " + itemPrice +
                        "\nitemQuantity -> " + itemQuantity +
                        "\namountOfProteins -> " + list.get(list.size() - 1).getNutritionalValue().getAmountOfProtein() +
                        "\namountOfCarbohydrates -> " + list.get(list.size() - 1).getNutritionalValue().getAmountOfCarbohydrate() +
                        "\namountOfFats -> " + list.get(list.size() - 1).getNutritionalValue().getAmountOfFat()
                );
                alert.showAndWait();
                textFieldId.clear();
                textFieldName.clear();
                textFieldPrice.clear();
                textFieldQuantity.clear();
                textFieldProteins.clear();
                textFieldCarbohydrates.clear();
                textFieldFats.clear();
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
