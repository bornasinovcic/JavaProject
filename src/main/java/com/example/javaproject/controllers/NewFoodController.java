package com.example.javaproject.controllers;

import com.example.javaproject.entities.Food;
import com.example.javaproject.entities.NutritionalValue;
import com.example.javaproject.entities.User;
import com.example.javaproject.exceptions.DuplicateItemIdException;
import com.example.javaproject.exceptions.DuplicateItemNameException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.example.javaproject.database.DatabaseHandling.getAllFoodItems;
import static com.example.javaproject.database.DatabaseHandling.insertNewFoodItem;
import static com.example.javaproject.entities.Random.randomString;

public class NewFoodController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewFoodController.class);
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
    private void onRandomButtonClick() {
        String madeString = randomString();
        textFieldId.setText(madeString);
    }

    @FXML
    private void onButtonClick() {
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
        if (itemCarbohydratesString.isEmpty())
            stringBuilder.append("You forgot to input carbohydrate value of that food.\n");
        if (itemFatsString.isEmpty()) stringBuilder.append("You forgot to input fat value of that food.\n");

        if (stringBuilder.isEmpty()) {
            try (FileInputStream file = new FileInputStream("files/loggedUser.ser");
                 ObjectInputStream in = new ObjectInputStream(file)) {
                User user = (User) in.readObject();
                BigDecimal itemPrice = new BigDecimal(itemPriceString);
                Integer itemQuantity = Integer.valueOf(itemQuantityString);
                Integer amountOfProteins = Integer.valueOf(itemProteinsString);
                Integer amountOfCarbohydrates = Integer.valueOf(itemCarbohydratesString);
                Integer amountOfFats = Integer.valueOf(itemFatsString);
                List<Food> list = getAllFoodItems();
                Food food = new Food.FoodBuilder()
                        .setItemId(itemId)
                        .setItemName(itemName)
                        .setItemPrice(itemPrice)
                        .setItemQuantity(itemQuantity)
                        .setNutritionalValue(new NutritionalValue.NutritionalValueBuilder()
                                .setAmountOfProteins(amountOfProteins)
                                .setAmountOfCarbohydrates(amountOfCarbohydrates)
                                .setAmountOfFats(amountOfFats)
                                .createNutritionalValue())
                        .createFood();
                testForDuplicateFoodId(list, itemId);
                testForDuplicateFoodName(list, itemName);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("New food added.");
                alert.setHeaderText("Information about new food item.");
                String string = "Id -> [" + itemId + "]" +
                        "\nName -> [" + itemName + "]" +
                        "\nPrice -> [" + itemPrice + "]" +
                        "\nQuantity -> [" + itemQuantity + "]" +
                        "\nProteins -> [" + food.getNutritionalValue().getAmountOfProtein() + "]" +
                        "\nCarbohydrates -> [" + food.getNutritionalValue().getAmountOfCarbohydrate() + "]" +
                        "\nFats -> [" + food.getNutritionalValue().getAmountOfFat() + "]";
                alert.setContentText(string);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.isPresent() && buttonType.get() == ButtonType.OK) {
                    insertNewFoodItem(food);
                    LOGGER.info("Inserted new food item.");
                    textFieldId.clear();
                    textFieldName.clear();
                    textFieldPrice.clear();
                    textFieldQuantity.clear();
                    textFieldProteins.clear();
                    textFieldCarbohydrates.clear();
                    textFieldFats.clear();
                }
            } catch (DuplicateItemIdException | DuplicateItemNameException e) {
                LOGGER.error(e.getMessage());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error alert message");
                alert.setHeaderText(e.getMessage());
                alert.showAndWait();
            } catch (NumberFormatException e) {
                LOGGER.warn("Some of the fields have a wrong data type.");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning alert message");
                alert.setHeaderText("Some of the fields have a wrong data type.\n" +
                        "Please make sure you have inputted everything correctly.");
                alert.showAndWait();
            } catch (IOException | ClassNotFoundException e) {
                LOGGER.error("No user is signed in.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error alert message");
                alert.setHeaderText("No user is signed in.\nPlease sign in on the main screen.");
                alert.showAndWait();
            }
        } else {
            LOGGER.error("Please fill out empty fields.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error alert message");
            alert.setHeaderText("Please fill out empty fields.");
            alert.setContentText(stringBuilder.toString());
            alert.showAndWait();
        }
    }

    private void testForDuplicateFoodId(List<Food> list, String id) throws DuplicateItemIdException {
        for (Food food : list)
            if (food.getItemId().equals(id))
                throw new DuplicateItemIdException("This food id already exists.");
    }

    private void testForDuplicateFoodName(List<Food> list, String itemName) throws DuplicateItemNameException {
        for (Food food : list)
            if (food.getItemName().equals(itemName))
                throw new DuplicateItemNameException("This food name already exists.");
    }
}
