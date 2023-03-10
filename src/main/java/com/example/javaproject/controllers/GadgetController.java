package com.example.javaproject.controllers;

import com.example.javaproject.entities.Gadget;
import com.example.javaproject.sorters.SortingGadgets;
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

import static com.example.javaproject.database.DatabaseHandling.getAllGadgetItems;

public class GadgetController {
    @FXML
    private TextField textFieldName;
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
    private void onKeyTyped() {
        String name = textFieldName.getText().toLowerCase();
        String warranty = textFieldWarranty.getText().toLowerCase();
        List<Gadget> list = new ArrayList<>(gadgetList);
        list = list.stream()
                .filter(gadget -> gadget.getItemName().toLowerCase().contains(name))
                .filter(gadget -> gadget.getItemWarrantyInMonths().toString().toLowerCase().contains(warranty))
                .toList();
        tableViewGadget.setItems(FXCollections.observableList(list));
    }
    @FXML
    private void initialize() {
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
