package com.example.javaproject.database;

import com.example.javaproject.entities.Food;
import com.example.javaproject.entities.NutritionalValue;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseHandling {
    private static Connection connectingToDatabase() throws IOException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("files/database.properties"));
        return DriverManager.getConnection(properties.getProperty("link"),
                properties.getProperty("ime"),
                properties.getProperty("lozinka"));
    }

    public static List<Food> getAllFoodItems() {
        List<Food> list = new ArrayList<>();
        try (Connection connection = connectingToDatabase()) {
            if (connection != null) System.out.println("Connected to database.");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM food ;");
            while (resultSet.next()) {
                String food_id = resultSet.getString("food_id");
                String food_name = resultSet.getString("food_name");
                BigDecimal food_price = resultSet.getBigDecimal("food_price");
                Integer food_quantity = resultSet.getInt("food_quantity");
                Integer food_proteins = resultSet.getInt("food_proteins");
                Integer food_carbohydrates = resultSet.getInt("food_carbohydrates");
                Integer food_fats = resultSet.getInt("food_fats");
                list.add(new Food(food_id, food_name, food_price, food_quantity, new NutritionalValue(food_proteins, food_carbohydrates, food_fats)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("There was a problem connecting to database.");
        }
        return list;
    }
}
