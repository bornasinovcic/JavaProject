package com.example.javaproject.controllers;

import com.example.javaproject.entities.Gadget;
import com.example.javaproject.entities.Random;
import com.example.javaproject.exceptions.DuplicateItemIdException;
import com.example.javaproject.exceptions.DuplicateItemNameException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.util.List;

import static com.example.javaproject.database.DatabaseHandling.getAllGadgetItems;
import static com.example.javaproject.database.DatabaseHandling.insertNewGadgetItem;

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
    protected void onRandomButtonClick() {
        String madeString = new Random().randomString();
        textFieldId.setText(madeString);
    }


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
                Integer itemQuantity = Integer.valueOf(itemQuantityString);
                Integer itemWarrantyInMonths = Integer.valueOf(itemWarrantyInMonthsString);
                List<Gadget> list = getAllGadgetItems();
                Gadget gadget = new Gadget(itemId, itemName, itemPrice, itemQuantity, itemWarrantyInMonths);
                testForDuplicateGadgetId(list, itemId);
                testForDuplicateGadgetName(list, itemName);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("New gadget added.");
                alert.setHeaderText("Information about new gadget item.");
                String string = "Id -> [" + itemId + "]" +
                        "\nName -> [" + itemName + "]" +
                        "\nPrice -> [" + itemPrice + "]" +
                        "\nQuantity -> [" + itemQuantity + "]" +
                        "\nWarranty -> [" + itemWarrantyInMonths + "]";
                alert.setContentText(string);
                alert.showAndWait();
                insertNewGadgetItem(gadget);
                textFieldId.clear();
                textFieldName.clear();
                textFieldPrice.clear();
                textFieldQuantity.clear();
                textFieldWarranty.clear();
            } catch (DuplicateItemIdException | DuplicateItemNameException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error alert message");
                alert.setHeaderText(e.getMessage());
                alert.showAndWait();
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning alert message");
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

    private void testForDuplicateGadgetId(List<Gadget> list, String id) throws DuplicateItemIdException {
        for (Gadget gadget : list)
            if (gadget.getItemId().equals(id))
                throw new DuplicateItemIdException("This gadget id already exists.");
    }

    private void testForDuplicateGadgetName(List<Gadget> list, String itemName) throws DuplicateItemNameException {
        for (Gadget gadget : list)
            if (gadget.getItemName().equals(itemName))
                throw new DuplicateItemNameException("This gadget name already exists.");
    }

}