package com.example.javaproject.database;

import com.example.javaproject.entities.Food;
import com.example.javaproject.entities.Gadget;
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
    public static List<Gadget> getAllGadgetItems() {
        List<Gadget> list = new ArrayList<>();
        try (Connection connection = connectingToDatabase()) {
            if (connection != null) System.out.println("Connected to database.");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM gadget ;");
            while (resultSet.next()) {
                String gadget_id = resultSet.getString("gadget_id");
                String gadget_name = resultSet.getString("gadget_name");
                BigDecimal gadget_price = resultSet.getBigDecimal("gadget_price");
                Integer gadget_quantity = resultSet.getInt("gadget_quantity");
                Integer gadget_warranty = resultSet.getInt("gadget_warranty");
                list.add(new Gadget(gadget_id, gadget_name, gadget_price, gadget_quantity, gadget_warranty));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("There was a problem connecting to database.");
        }
        return list;
    }

    public static void insertNewFoodItem(Food food) {
        try (Connection connection = connectingToDatabase()) {
            if (connection != null) System.out.println("Connected to database.");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO food (food_id, food_name, food_price, food_quantity, food_proteins, food_carbohydrates, food_fats) VALUES (?, ?, ?, ?, ?, ?, ?) ;");
            preparedStatement.setString(1, food.getItemId());
            preparedStatement.setString(2, food.getItemName());
            preparedStatement.setBigDecimal(3, food.getItemPrice());
            preparedStatement.setInt(4, food.getItemQuantity());
            preparedStatement.setInt(5, food.getNutritionalValue().getAmountOfProtein());
            preparedStatement.setInt(6, food.getNutritionalValue().getAmountOfCarbohydrate());
            preparedStatement.setInt(7, food.getNutritionalValue().getCaloriesInFat());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("There was a problem connecting to database.");
        }
    }
    public static void insertNewGadgetItem(Gadget gadget) {
        try (Connection connection = connectingToDatabase()) {
            if (connection != null) System.out.println("Connected to database.");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO gadget (gadget_id, gadget_name, gadget_price, gadget_quantity, gadget_warranty) VALUES (?, ?, ?, ?, ?) ;");
            preparedStatement.setString(1, gadget.getItemId());
            preparedStatement.setString(2, gadget.getItemName());
            preparedStatement.setBigDecimal(3, gadget.getItemPrice());
            preparedStatement.setInt(4, gadget.getItemQuantity());
            preparedStatement.setInt(5, gadget.getItemWarrantyInMonths());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("There was a problem connecting to database.");
        }
    }
}
