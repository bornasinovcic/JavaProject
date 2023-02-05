package com.example.javaproject.controllers;

import com.example.javaproject.entities.Roles;
import com.example.javaproject.entities.User;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.javaproject.entities.Hash.*;
import static com.example.javaproject.entities.Random.randomString;
import static com.example.javaproject.files.FilesHandling.addNewUser;
import static com.example.javaproject.files.FilesHandling.getUsers;

public class MainController {
    @FXML
    private TextField textFieldId;
    @FXML
    private TextField textFieldName;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordFieldLogIn;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private ComboBox<String> comboBoxUsers;

    @FXML
    protected void onRandomButtonClick() {
        String madeString = randomString();
        textFieldId.setText(madeString);
    }

    @FXML
    protected void signInButton() {
        String logInName = comboBoxUsers.getValue();
        String passwordLogIn = passwordFieldLogIn.getText();

        try {
            List<User> list = getUsers();
            for (User user : list) {
                if (user.getUserName().equals(logInName)) {
                    String decryptedPass = decryption(user.getUserPassword());

                    System.out.println("user.getUserPassword() -> " + user.getUserPassword());
                    System.out.println("decryptedPass -> " + decryptedPass);
                    System.out.println("passwordLogIn -> " + passwordLogIn);
                    if (decryptedPass.equals(passwordLogIn)) System.out.println("you have logged in ass user");
                    else System.out.println("something want wrong");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    protected void makeAccountButton() {
        List<User> list = new ArrayList<>();
        String id = textFieldId.getText();
        String name = textFieldName.getText();
        String password = passwordField.getText();
        String roleString = comboBox.getValue();
        Roles role = null;
        for (Roles roles : Roles.values())
            if (roleString.equals(roles.getRole()))
                role = roles;
        User user = new User.UserBuilder()
                .setItemId(id)
                .setUserName(name)
                .setUserPassword(encryption(password))
                .setRole(role)
                .createUser();
        list.add(user);
        System.out.println(user.getItemId() + " " +
                user.getUserName() + " " +
                user.getUserPassword() + " " +
                user.getRole());
        try {
            addNewUser(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void initialize() {
        try {
            List<User> list = getUsers();
            for (User user : list) comboBoxUsers.getItems().add(user.getUserName());
            for (Roles roles : Roles.values()) comboBox.getItems().add(roles.getRole());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
