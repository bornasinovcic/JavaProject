package com.example.javaproject.controllers;

import com.example.javaproject.entities.Food;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.example.javaproject.files.File.getFoodItems;

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
    private static List<Food> FOOD_LIST = new ArrayList<>();

    @FXML
    protected void onKeyTyped() {
        String s = textField.getText();
        List<Food> list = new ArrayList<>(FOOD_LIST);
        list = list.stream()
                .filter(food -> food.getItemName().contains(s))
                .toList();
        tableViewFood.setItems(FXCollections.observableList(list));
        System.out.println("onKeyTyped");
    }

    @FXML
    public void initialize() {
        try {
            FOOD_LIST.clear();
            FOOD_LIST = getFoodItems();
            tableColumnId.setCellValueFactory(foodStringCellDataFeatures -> new SimpleStringProperty(foodStringCellDataFeatures.getValue().getItemId()));
            tableColumnName.setCellValueFactory(foodStringCellDataFeatures -> new SimpleStringProperty(foodStringCellDataFeatures.getValue().getItemName()));
            tableColumnPrice.setCellValueFactory(foodBigDecimalCellDataFeatures -> new SimpleObjectProperty<>(foodBigDecimalCellDataFeatures.getValue().getItemPrice()));
            tableColumnQuantity.setCellValueFactory(foodIntegerCellDataFeatures -> new SimpleObjectProperty<>(foodIntegerCellDataFeatures.getValue().getItemQuantity()));
            tableColumnProteins.setCellValueFactory(foodIntegerCellDataFeatures -> new SimpleObjectProperty<>(foodIntegerCellDataFeatures.getValue().getNutritionalValue().getAmountOfProtein()));
            tableColumnCarbohydrates.setCellValueFactory(foodIntegerCellDataFeatures -> new SimpleObjectProperty<>(foodIntegerCellDataFeatures.getValue().getNutritionalValue().getAmountOfCarbohydrate()));
            tableColumnFats.setCellValueFactory(foodIntegerCellDataFeatures -> new SimpleObjectProperty<>(foodIntegerCellDataFeatures.getValue().getNutritionalValue().getAmountOfFat()));
            tableViewFood.setItems(FXCollections.observableList(FOOD_LIST));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
