package com.example.javaproject.controllers;

import com.example.javaproject.entities.Gadget;
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

import static com.example.javaproject.database.DatabaseHandling.getAllGadgetItems;
import static com.example.javaproject.database.DatabaseHandling.insertNewGadgetItem;
import static com.example.javaproject.entities.Random.randomString;

public class NewGadgetController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewGadgetController.class);
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
        String madeString = randomString();
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
            try (FileInputStream file = new FileInputStream("files/loggedUser.ser");
                 ObjectInputStream in = new ObjectInputStream(file)) {
                User user = (User) in.readObject();
                BigDecimal itemPrice = new BigDecimal(itemPriceString);
                Integer itemQuantity = Integer.valueOf(itemQuantityString);
                Integer itemWarrantyInMonths = Integer.valueOf(itemWarrantyInMonthsString);
                List<Gadget> list = getAllGadgetItems();
                Gadget gadget = new Gadget.GadgetBuilder()
                        .setItemId(itemId)
                        .setItemName(itemName)
                        .setItemPrice(itemPrice)
                        .setItemQuantity(itemQuantity)
                        .setItemWarrantyInMonths(itemWarrantyInMonths)
                        .createGadget();
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
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.isPresent() && buttonType.get() == ButtonType.OK) {
                    insertNewGadgetItem(gadget);
                    LOGGER.info("Inserted new gadget item.");
                    textFieldId.clear();
                    textFieldName.clear();
                    textFieldPrice.clear();
                    textFieldQuantity.clear();
                    textFieldWarranty.clear();
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
            }
            catch (IOException | ClassNotFoundException e) {
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