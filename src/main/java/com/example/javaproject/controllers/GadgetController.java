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

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.example.javaproject.files.File.getGadgetItems;

public class GadgetController {
    @FXML
    private TextField textField;
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
    protected void onKeyTyped() {
        String s = textField.getText().toLowerCase();
        List<Gadget> list = new ArrayList<>(gadgetList);
        list = list.stream()
                .filter(gadget -> gadget.getItemName().toLowerCase().contains(s))
                .toList();
        tableViewGadget.setItems(FXCollections.observableList(list));
    }
    @FXML
    public void initialize() {
        try {
            gadgetList.clear();
            gadgetList = getGadgetItems();
            gadgetList.sort(new SortingGadgets());
            tableColumnId.setCellValueFactory(gadgetStringCellDataFeatures -> new SimpleStringProperty(gadgetStringCellDataFeatures.getValue().getItemId()));
            tableColumnName.setCellValueFactory(gadgetStringCellDataFeatures -> new SimpleStringProperty(gadgetStringCellDataFeatures.getValue().getItemName()));
            tableColumnPrice.setCellValueFactory(gadgetStringCellDataFeatures -> new SimpleObjectProperty<>(gadgetStringCellDataFeatures.getValue().getItemPrice()));
            tableColumnQuantity.setCellValueFactory(gadgetStringCellDataFeatures -> new SimpleObjectProperty<>(gadgetStringCellDataFeatures.getValue().getItemQuantity()));
            tableColumnWarranty.setCellValueFactory(gadgetStringCellDataFeatures -> new SimpleObjectProperty<>(gadgetStringCellDataFeatures.getValue().getItemWarrantyInMonths()));
            tableViewGadget.setItems(FXCollections.observableList(gadgetList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
