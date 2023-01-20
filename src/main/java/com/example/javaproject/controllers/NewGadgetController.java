package com.example.javaproject.controllers;

import com.example.javaproject.entities.Food;
import com.example.javaproject.entities.Gadget;
import com.example.javaproject.exceptions.DuplicateItem;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static com.example.javaproject.database.DatabaseHandling.getAllGadgetItems;
import static com.example.javaproject.files.File.*;

public class NewGadgetController {
    @FXML
    private TextField textFieldId;
    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldPrice;
    @FXML
    private TextField textFieldQuantity;
    @FXML
    private TextField textFieldWarranty;

    @FXML
    protected void onButtonClick() {
        String itemId = textFieldId.getText();
        String itemName = textFieldName.getText();
        String itemPriceString = textFieldPrice.getText();
        String itemQuantityString = textFieldQuantity.getText();
        String itemWarrantyInMonthsString = textFieldWarranty.getText();

        StringBuilder stringBuilder = new StringBuilder();

        if (itemId.isEmpty()) stringBuilder.append("You forgot to input id of that gadget.\n");
        if (itemName.isEmpty()) stringBuilder.append("You forgot to input name of that gadget.\n");
        if (itemPriceString.isEmpty()) stringBuilder.append("You forgot to input price of that gadget.\n");
        if (itemQuantityString.isEmpty()) stringBuilder.append("You forgot to input quantity of that gadget.\n");
        if (itemWarrantyInMonthsString.isEmpty())
            stringBuilder.append("You forgot to input warranty of that gadget.\n");

        if (stringBuilder.isEmpty()) {
            try {
                BigDecimal itemPrice = new BigDecimal(itemPriceString);
                Integer itemQuantity  = Integer.valueOf(itemQuantityString);
                Integer itemWarrantyInMonths = Integer.valueOf(itemWarrantyInMonthsString);
                List<Gadget> list = getAllGadgetItems();
                Gadget gadget = new Gadget(itemId, itemName, itemPrice, itemQuantity, itemWarrantyInMonths);
                testForDuplicateFoodItem(list, gadget);
                list.add(gadget);
                addNewGadgetItem(list);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Correct input of data for gadget object.");
                alert.setTitle("New gadget added.");
                alert.setContentText(
                        "itemId -> " + itemId +
                                "\nitemName -> " + itemName +
                                "\nitemPrice -> " + itemPrice +
                                "\nitemQuantity -> " + itemQuantity +
                                "\nitemWarrantyInMonths -> " + itemWarrantyInMonths);
                alert.showAndWait();
                textFieldId.clear();
                textFieldName.clear();
                textFieldPrice.clear();
                textFieldQuantity.clear();
                textFieldWarranty.clear();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DuplicateItem e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error alert message");
                alert.setHeaderText("Some of the fields have a wrong data type.\n" +
                        "Please make sure you have inputted everything correctly.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error alert message");
            alert.setHeaderText("Please fill out empty fields");
            alert.setContentText(stringBuilder.toString());
            alert.showAndWait();
        }
    }
    private void testForDuplicateFoodItem(List<Gadget> list, Gadget gadget0) throws DuplicateItem {
        for (Gadget gadget1 : list) {
            if (gadget1.equals(gadget0)) {
                throw new DuplicateItem("This gadget already exists.");
            }
        }
    }

}