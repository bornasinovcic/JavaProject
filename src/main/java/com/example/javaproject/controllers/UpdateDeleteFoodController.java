package com.example.javaproject.controllers;

import com.example.javaproject.entities.Food;
import com.example.javaproject.entities.NutritionalValue;
import com.example.javaproject.exceptions.DuplicateItemIdException;
import com.example.javaproject.exceptions.DuplicateItemNameException;
import com.example.javaproject.exceptions.SelectedItemException;
import com.example.javaproject.sorters.SortingFoods;
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

public class UpdateDeleteFoodController {
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
    private TableColumn<Food, String> tableColumnId;
    @FXML
    private TableColumn<Food, String> tableColumnName;
    @FXML
    private TableColumn<Food, BigDecimal> tableColumnPrice;
    @FXML
    private TableColumn<Food, Integer> tableColumnQuantity;
    @FXML
    private TableColumn<Food, Integer> tableColumnProteins;
    @FXML
    private TableColumn<Food, Integer> tableColumnCarbohydrates;
    @FXML
    private TableColumn<Food, Integer> tableColumnFats;
    @FXML
    private TableView<Food> tableViewFood;
    private static List<Food> foodList = new ArrayList<>();

    @FXML
    protected void onUpdateButtonClick() {
        String itemId = textFieldId.getText();
        String itemName = textFieldName.getText();
        String itemPriceString = textFieldPrice.getText();
        String itemQuantityString = textFieldQuantity.getText();
        String itemProteinsString = textFieldProteins.getText();
        String itemCarbohydratesString = textFieldCarbohydrates.getText();
        String itemFatsString = textFieldFats.getText();

        Food selectedItem = tableViewFood.getSelectionModel().getSelectedItem();
        try {
            isSelectedItemNull(selectedItem);
            Food newMadeItem = new Food();
            NutritionalValue nutritionalValue = new NutritionalValue();

            newMadeItem.setItemId(itemId.isEmpty() ? selectedItem.getItemId() : itemId);
            newMadeItem.setItemName(itemName.isEmpty() ? selectedItem.getItemName() : itemName);
            newMadeItem.setItemPrice(itemPriceString.isEmpty() ? selectedItem.getItemPrice() : new BigDecimal(itemPriceString));
            newMadeItem.setItemQuantity(itemQuantityString.isEmpty() ? selectedItem.getItemQuantity() : Integer.valueOf(itemQuantityString));
            nutritionalValue.setAmountOfProtein(itemProteinsString.isEmpty() ? selectedItem.getNutritionalValue().getAmountOfProtein() : Integer.valueOf(itemProteinsString));
            nutritionalValue.setAmountOfCarbohydrate(itemCarbohydratesString.isEmpty() ? selectedItem.getNutritionalValue().getAmountOfCarbohydrate() : Integer.valueOf(itemCarbohydratesString));
            nutritionalValue.setAmountOfFat(itemFatsString.isEmpty() ? selectedItem.getNutritionalValue().getAmountOfFat() : Integer.valueOf(itemFatsString));
            newMadeItem.setNutritionalValue(nutritionalValue);

            List<Food> list = getAllFoodItems();

            testForDuplicateFoodId(list, itemId);
            testForDuplicateFoodName(list, itemName);

            updateFoodWithId(newMadeItem, selectedItem.getItemId());
            initialize();
            textFieldId.clear();
            textFieldName.clear();
            textFieldPrice.clear();
            textFieldQuantity.clear();
            textFieldProteins.clear();
            textFieldCarbohydrates.clear();
            textFieldFats.clear();
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



    @FXML
    protected void onDeleteButtonClick() {
        Food food = tableViewFood.getSelectionModel().getSelectedItem();
        try {
            isSelectedItemNull(food);
            deleteFoodWithId(food.getItemId());
            initialize();
        } catch (SelectedItemException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(e.getMessage());
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
    }
    private void isSelectedItemNull(Food selectedItem) throws SelectedItemException {
        if (selectedItem == null)
            throw new SelectedItemException("You did not select an item in table view");
    }


    public void initialize() {
        foodList.clear();
        foodList = getAllFoodItems();
        foodList.sort(new SortingFoods());
        tableColumnId.setCellValueFactory(foodStringCellDataFeatures -> new SimpleStringProperty(foodStringCellDataFeatures.getValue().getItemId()));
        tableColumnName.setCellValueFactory(foodStringCellDataFeatures -> new SimpleStringProperty(foodStringCellDataFeatures.getValue().getItemName()));
        tableColumnPrice.setCellValueFactory(foodBigDecimalCellDataFeatures -> new SimpleObjectProperty<>(foodBigDecimalCellDataFeatures.getValue().getItemPrice()));
        tableColumnQuantity.setCellValueFactory(foodIntegerCellDataFeatures -> new SimpleObjectProperty<>(foodIntegerCellDataFeatures.getValue().getItemQuantity()));
        tableColumnProteins.setCellValueFactory(foodIntegerCellDataFeatures -> new SimpleObjectProperty<>(foodIntegerCellDataFeatures.getValue().getNutritionalValue().getAmountOfProtein()));
        tableColumnCarbohydrates.setCellValueFactory(foodIntegerCellDataFeatures -> new SimpleObjectProperty<>(foodIntegerCellDataFeatures.getValue().getNutritionalValue().getAmountOfCarbohydrate()));
        tableColumnFats.setCellValueFactory(foodIntegerCellDataFeatures -> new SimpleObjectProperty<>(foodIntegerCellDataFeatures.getValue().getNutritionalValue().getAmountOfFat()));
        tableViewFood.setItems(FXCollections.observableList(foodList));
    }

}
