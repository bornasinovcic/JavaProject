package com.example.javaproject.controllers;

import com.example.javaproject.entities.Food;
import com.example.javaproject.entities.Gadget;
import com.example.javaproject.entities.Item;
import com.example.javaproject.generics.Changes;
import com.example.javaproject.sorters.SortingChanges;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.example.javaproject.entities.DataRefresh.deserialization;


public class ChangesController {
    @FXML
    private TableView<Changes<Item>> tableView;
    @FXML
    private TableColumn<Changes<Item>, Item> before;
    @FXML
    private TableColumn<Changes<Item>, Item> after;
    @FXML
    private TableColumn<Changes<Item>, String> role;
    @FXML
    private TableColumn<Changes<Item>, String> localDateTime;

    private List<Changes<Item>> list = new ArrayList<>();
    @FXML
    private void initialize() {
        list.clear();
        Thread thread = new Thread(() -> {
            list = deserialization();
            list.sort(new SortingChanges());
            before.setCellValueFactory(changeItemCellDataFeatures -> {
                if (changeItemCellDataFeatures.getValue().getBefore().getClass() == Food.class) {
                    return new SimpleObjectProperty<>(changeItemCellDataFeatures.getValue().getBefore());
                }
                if (changeItemCellDataFeatures.getValue().getBefore().getClass() == Gadget.class) {
                    return new SimpleObjectProperty<>(changeItemCellDataFeatures.getValue().getBefore());
                }
                return null;
            });
            after.setCellValueFactory(changeItemCellDataFeatures -> {
                if (changeItemCellDataFeatures.getValue().getBefore().getClass() == Food.class) {
                    return new SimpleObjectProperty<>(changeItemCellDataFeatures.getValue().getAfter());
                }
                if (changeItemCellDataFeatures.getValue().getBefore().getClass() == Gadget.class) {
                    return new SimpleObjectProperty<>(changeItemCellDataFeatures.getValue().getAfter());
                }
                return null;
            });
            role.setCellValueFactory(changeItemCellDataFeatures -> new SimpleObjectProperty<>(changeItemCellDataFeatures.getValue().getUser().getRole().name()));
            localDateTime.setCellValueFactory(changeLocalDateTimeCellDataFeatures -> new SimpleObjectProperty<>(changeLocalDateTimeCellDataFeatures.getValue().getLocalDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyy HH:mm"))));
            tableView.setItems(FXCollections.observableList(list));
        });
        thread.start();
    }
}