package com.example.javaproject.controllers;

import com.example.javaproject.entities.Gadget;
import com.example.javaproject.entities.Random;
import com.example.javaproject.exceptions.DuplicateItemIdException;
import com.example.javaproject.exceptions.DuplicateItemNameException;
import com.example.javaproject.exceptions.SelectedItemException;
import com.example.javaproject.sorters.SortingGadgets;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.example.javaproject.database.DatabaseHandling.*;

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
        String madeString = new Random().randomString();
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
            Gadget newMadeItem = new Gadget();

            newMadeItem.setItemId(itemId.isEmpty() ? selectedItem.getItemId() : itemId);
            newMadeItem.setItemName(itemName.isEmpty() ? selectedItem.getItemName() : itemName);
            newMadeItem.setItemPrice(itemPriceString.isEmpty() ? selectedItem.getItemPrice() : new BigDecimal(itemPriceString));
            newMadeItem.setItemQuantity(itemQuantityString.isEmpty() ? selectedItem.getItemQuantity() : Integer.valueOf(itemQuantityString));
            newMadeItem.setItemWarrantyInMonths(itemWarrantyInMonthsString.isEmpty() ? selectedItem.getItemWarrantyInMonths() : Integer.valueOf(itemWarrantyInMonthsString));

            List<Gadget> list = getAllGadgetItems();

            testForDuplicateFoodId(list, itemId);
            testForDuplicateFoodName(list, itemName);

            updateGadgetWithId(newMadeItem, selectedItem.getItemId());
            initialize();
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

    private void testForDuplicateFoodId(List<Gadget> list, String id) throws DuplicateItemIdException {
        for (Gadget gadget : list)
            if (gadget.getItemId().equals(id))
                throw new DuplicateItemIdException("This food id already exists.");
    }

    private void testForDuplicateFoodName(List<Gadget> list, String itemName) throws DuplicateItemNameException {
        for (Gadget gadget : list)
            if (gadget.getItemName().equals(itemName))
                throw new DuplicateItemNameException("This food name already exists.");
    }


    @FXML
    protected void onDeleteButtonClick() {
        Gadget gadget = tableViewGadget.getSelectionModel().getSelectedItem();
        try {
            isSelectedItemNull(gadget);
            deleteGadgetWithId(gadget.getItemId());
            initialize();
        } catch (SelectedItemException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(e.getMessage());
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
    }

    private void isSelectedItemNull(Gadget selectedItem) throws SelectedItemException {
        if (selectedItem == null)
            throw new SelectedItemException("You did not select an item in table view");
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
