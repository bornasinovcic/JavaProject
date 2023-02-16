package com.example.javaproject.controllers;

import com.example.javaproject.entities.Food;
import com.example.javaproject.sorters.SortingFoods;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.example.javaproject.database.DatabaseHandling.getAllFoodItems;

public class FoodController {
    @FXML
    private TextField textField;
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
    private void onKeyTyped() {
        String s = textField.getText().toLowerCase();
        List<Food> list = new ArrayList<>(foodList);
        list = list.stream()
                .filter(food -> food.getItemName().toLowerCase().contains(s))
                .toList();
        tableViewFood.setItems(FXCollections.observableList(list));
    }

    @FXML
    private void initialize() {
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
