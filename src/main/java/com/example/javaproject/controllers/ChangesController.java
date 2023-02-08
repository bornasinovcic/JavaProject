package com.example.javaproject.controllers;

import com.example.javaproject.entities.Change;
import com.example.javaproject.entities.Item;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ChangesController {
    @FXML
    private TableView<Change> tableView;
    @FXML
    private TableColumn<Change, Item> before;
    @FXML
    private TableColumn<Change, Item> after;
    @FXML
    private TableColumn<Change, String> role;
    @FXML
    private TableColumn<Change, String> localDateTime;

    private List<Change> list = new ArrayList<>();
    @FXML
    private void initialize() {
        list.clear();
        File filepath = new File("files/changes.ser");
        try (FileInputStream file = new FileInputStream(filepath);
             ObjectInputStream in = new ObjectInputStream(file);) {
            list = (List<Change>) in.readObject();
            System.out.println(filepath + " deserialized");
            list = list.stream().sorted((o1, o2) -> o2.getLocalDateTime().compareTo(o1.getLocalDateTime())).toList();
            before.setCellValueFactory(changeItemCellDataFeatures -> new SimpleObjectProperty<>(changeItemCellDataFeatures.getValue().getBefore()));
            after.setCellValueFactory(changeItemCellDataFeatures -> new SimpleObjectProperty<>(changeItemCellDataFeatures.getValue().getAfter()));
            role.setCellValueFactory(changeItemCellDataFeatures -> new SimpleStringProperty(changeItemCellDataFeatures.getValue().getUser().getRole().getRole()));
            localDateTime.setCellValueFactory(changeLocalDateTimeCellDataFeatures -> new SimpleObjectProperty<>(changeLocalDateTimeCellDataFeatures.getValue().getLocalDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyy HH:mm"))));
            tableView.setItems(FXCollections.observableList(list));
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}