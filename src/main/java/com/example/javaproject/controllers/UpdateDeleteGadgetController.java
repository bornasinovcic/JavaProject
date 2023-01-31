package com.example.javaproject.controllers;

import com.example.javaproject.entities.Gadget;
import com.example.javaproject.exceptions.DuplicateItemIdException;
import com.example.javaproject.exceptions.DuplicateItemNameException;
import com.example.javaproject.exceptions.SelectedItemException;
import com.example.javaproject.sorters.SortingGadgets;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.javaproject.database.DatabaseHandling.*;
import static com.example.javaproject.entities.Random.randomString;

public class UpdateDeleteGadgetController {
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
    private TableColumn<Gadget, String> tableColumnId;
    @FXML
    private TableColumn<Gadget, String> tableColumnName;
    @FXML
    private TableColumn<Gadget, BigDecimal> tableColumnPrice;
    @FXML
    private TableColumn<Gadget, Integer> tableColumnQuantity;
    @FXML
    private TableColumn<Gadget, Integer> tableColumnWarranty;
    @FXML
    private TableView<Gadget> tableViewGadget;
    private static List<Gadget> gadgetList = new ArrayList<>();

    @FXML
    protected void onRandomButtonClick() {
        String madeString = randomString();
        textFieldId.setText(madeString);
    }

    @FXML
    protected void onUpdateButtonClick() {
        String itemId = textFieldId.getText();
        String itemName = textFieldName.getText();
        String itemPriceString = textFieldPrice.getText();
        String itemQuantityString = textFieldQuantity.getText();
        String itemWarrantyInMonthsString = textFieldWarranty.getText();

        Gadget selectedItem = tableViewGadget.getSelectionModel().getSelectedItem();
        try {
            isSelectedItemNull(selectedItem);
            Gadget newMadeItem = new Gadget.GadgetBuilder()
                    .setItemId(itemId.isEmpty() ? selectedItem.getItemId() : itemId)
                    .setItemName(itemName.isEmpty() ? selectedItem.getItemName() : itemName)
                    .setItemPrice(itemPriceString.isEmpty() ? selectedItem.getItemPrice() : new BigDecimal(itemPriceString))
                    .setItemQuantity(itemQuantityString.isEmpty() ? selectedItem.getItemQuantity() : Integer.valueOf(itemQuantityString))
                    .setItemWarrantyInMonths(itemWarrantyInMonthsString.isEmpty() ? selectedItem.getItemWarrantyInMonths() : Integer.valueOf(itemWarrantyInMonthsString))
                    .createGadget();
            List<Gadget> list = getAllGadgetItems();

            System.out.println("itemId -> " + itemId);
            System.out.println("itemName -> " + itemName);

            if (selectedItem.getItemId().equals(itemId)) itemId = "";
            if (selectedItem.getItemName().equals(itemName)) itemName = "";

            testForDuplicateGadgetId(list, itemId);
            testForDuplicateGadgetName(list, itemName);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Gadget item changed.");
            alert.setHeaderText("All changes are listed bellow.");
            String string = "old Id [" + selectedItem.getItemId() + "] new Id [" + newMadeItem.getItemId() + "]" +
                    "\nold Name [" + selectedItem.getItemName() + "] new Name [" + newMadeItem.getItemName() + "]" +
                    "\nold Price [" + selectedItem.getItemPrice() + "] new Price [" + newMadeItem.getItemPrice() + "]" +
                    "\nold Quantity [" + selectedItem.getItemQuantity() + "] new Quantity [" + newMadeItem.getItemQuantity() + "]" +
                    "\nold Warranty [" + selectedItem.getItemWarrantyInMonths() + "] new Warranty [" + newMadeItem.getItemWarrantyInMonths() + "]";
            alert.setContentText(string);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.isPresent() && buttonType.get() == ButtonType.OK) {
                updateGadgetWithId(newMadeItem, selectedItem.getItemId());
                initialize();
                textFieldId.clear();
                textFieldName.clear();
                textFieldPrice.clear();
                textFieldQuantity.clear();
                textFieldWarranty.clear();
            }
        } catch (DuplicateItemIdException | DuplicateItemNameException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error alert message");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } catch (SelectedItemException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(e.getMessage());
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning alert message");
            alert.setHeaderText("Some of the fields have a wrong data type.\n" +
                    "Please make sure you have inputted everything correctly.");
            alert.showAndWait();
        }
    }

    private void testForDuplicateGadgetId(List<Gadget> list, String id) throws DuplicateItemIdException {
        for (Gadget gadget : list)
            if (gadget.getItemId().equals(id))
                throw new DuplicateItemIdException("This food id already exists.");
    }

    private void testForDuplicateGadgetName(List<Gadget> list, String itemName) throws DuplicateItemNameException {
        for (Gadget gadget : list)
            if (gadget.getItemName().equals(itemName))
                throw new DuplicateItemNameException("This food name already exists.");
    }

    private void isSelectedItemNull(Gadget selectedItem) throws SelectedItemException {
        if (selectedItem == null)
            throw new SelectedItemException("You did not select an item in table view");
    }

    @FXML
    protected void onDeleteButtonClick() {
        Gadget gadget = tableViewGadget.getSelectionModel().getSelectedItem();
        try {
            isSelectedItemNull(gadget);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deletion of item");
            alert.setHeaderText("Selected item will be deleted.");
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.isPresent() && buttonType.get() == ButtonType.OK) {
                deleteGadgetWithId(gadget.getItemId());
                initialize();
            }
        } catch (SelectedItemException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(e.getMessage());
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
    }


    @FXML
    public void initialize() {
        gadgetList.clear();
        gadgetList = getAllGadgetItems();
        gadgetList.sort(new SortingGadgets());
        tableColumnId.setCellValueFactory(gadgetStringCellDataFeatures -> new SimpleStringProperty(gadgetStringCellDataFeatures.getValue().getItemId()));
        tableColumnName.setCellValueFactory(gadgetStringCellDataFeatures -> new SimpleStringProperty(gadgetStringCellDataFeatures.getValue().getItemName()));
        tableColumnPrice.setCellValueFactory(gadgetStringCellDataFeatures -> new SimpleObjectProperty<>(gadgetStringCellDataFeatures.getValue().getItemPrice()));
        tableColumnQuantity.setCellValueFactory(gadgetStringCellDataFeatures -> new SimpleObjectProperty<>(gadgetStringCellDataFeatures.getValue().getItemQuantity()));
        tableColumnWarranty.setCellValueFactory(gadgetStringCellDataFeatures -> new SimpleObjectProperty<>(gadgetStringCellDataFeatures.getValue().getItemWarrantyInMonths()));
        tableViewGadget.setItems(FXCollections.observableList(gadgetList));
    }

}
