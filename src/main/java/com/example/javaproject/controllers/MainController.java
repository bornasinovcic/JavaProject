package com.example.javaproject.controllers;

import com.example.javaproject.entities.Roles;
import com.example.javaproject.entities.User;
import com.example.javaproject.exceptions.WrongPasswordException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.javaproject.entities.Hash.*;
import static com.example.javaproject.entities.Random.randomString;
import static com.example.javaproject.files.FilesHandling.addNewUser;
import static com.example.javaproject.files.FilesHandling.getUsers;

public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
    @FXML
    private TextField textFieldId;
    @FXML
    private TextField textFieldName;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordFieldLogIn;
    @FXML
    private ComboBox<Roles> comboBox;
    @FXML
    private ComboBox<User> comboBoxUsers;

    List<User> list = new ArrayList<>();

    @FXML
    protected void onRandomButtonClick() {
        String madeString = randomString();
        textFieldId.setText(madeString);
    }

    @FXML
    protected void signInButton() {
        User logInUser = comboBoxUsers.getValue();
        String passwordLogIn = passwordFieldLogIn.getText();
        try {
            List<User> list = getUsers();
            for (User user : list) {
                if (user.getUserName().equals(logInUser.getUserName()))
                    isPasswordCorrect(user, passwordLogIn);
            }
            comboBoxUsers.valueProperty().set(null);
            LOGGER.error("Successfully logged in.");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logged in");
            alert.setHeaderText("Successfully logged in.");
            alert.showAndWait();
        } catch (WrongPasswordException | IOException e) {
            LOGGER.error(e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error in user authentication.");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        }
        passwordFieldLogIn.clear();
    }

    private void isPasswordCorrect(User user, String passwordLogIn) {
        String decryptedPass = decryption(user.getUserPassword());
        if (!decryptedPass.equals(passwordLogIn))
            throw new WrongPasswordException("Wrong password for user " + user.getUserName() + ".");
    }

    @FXML
    protected void makeAccountButton() {
        try {
            List<User> list = getUsers();
            String id = textFieldId.getText();
            String name = textFieldName.getText();
            String password = passwordField.getText();
            Roles roleInput = comboBox.getValue();

            StringBuilder stringBuilder = new StringBuilder();
            if (id.isEmpty()) stringBuilder.append("You forgot to input id for that user.\n");
            if (name.isEmpty()) stringBuilder.append("You forgot to input name for that user.\n");
            if (password.isEmpty()) stringBuilder.append("You forgot to input password for that user.\n");
            if (roleInput == null) stringBuilder.append("You forgot to input role for that user.\n");

            if (stringBuilder.isEmpty()) {
                Roles role = null;
                for (Roles roles : Roles.values())
                    if (roleInput.getRole().equals(roles.getRole()))
                        role = roles;
                User user = new User.UserBuilder()
                        .setItemId(id)
                        .setUserName(name)
                        .setUserPassword(encryption(password))
                        .setRole(role)
                        .createUser();
                list.add(user);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("New user added.");
                alert.setHeaderText("Information about new user.");
                String string = "Id -> [" + id + "]" +
                        "\nName -> [" + name + "]" +
                        "\nPassword -> [" + password + "]" +
                        "\nRole -> [" + roleInput.getRole();
                alert.setContentText(string);
                alert.showAndWait();
                addNewUser(list);
                LOGGER.info("Made account for new user");
                textFieldId.clear();
                textFieldName.clear();
                passwordField.clear();
                comboBox.valueProperty().set(null);
            } else {
                LOGGER.error("Please fill out empty fields.");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error alert message");
                alert.setHeaderText("Please fill out empty fields");
                alert.setContentText(stringBuilder.toString());
                alert.showAndWait();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void initialize() {
        try {
            list.clear();
            list = getUsers();
            comboBoxUsers.getItems().removeAll(list);
            comboBoxUsers.getItems().addAll(list);
            comboBox.getItems().removeAll(Roles.values());
            comboBox.getItems().addAll(Roles.values());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
