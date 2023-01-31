module com.example.javaproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    exports com.example.javaproject.controllers;
    opens com.example.javaproject.controllers to javafx.fxml;
    exports com.example.javaproject.main;
    opens com.example.javaproject.main to javafx.fxml;
}