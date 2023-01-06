package com.example.javaproject.controllers;

import com.example.javaproject.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class MenuBarController {
    public void showNewFoodScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("newFoodScreen.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.getStage().setTitle("New Food Screen");
        Main.getStage().setScene(scene);
        Main.getStage().show();
    }
    public void showFoodScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("foodScreen.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.getStage().setTitle("Food Screen");
        Main.getStage().setScene(scene);
        Main.getStage().show();
    }
    public void showGadgetScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("gadgetScreen.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.getStage().setTitle("Gadget Screen");
        Main.getStage().setScene(scene);
        Main.getStage().show();
    }
}