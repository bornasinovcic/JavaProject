package com.example.javaproject.files;

import com.example.javaproject.entities.*;
import javafx.fxml.FXML;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FilesHandling {
    public static List<Gadget> getGadgetItems() throws IOException {
        List<Gadget> list = new ArrayList<>();
        FileReader fileReader = new FileReader("files/gadgets.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String itemName = bufferedReader.readLine();
            BigDecimal itemPrice = new BigDecimal(bufferedReader.readLine());
            Integer itemQuantity = Integer.valueOf(bufferedReader.readLine());
            Integer itemWarrantyInMonths = Integer.valueOf(bufferedReader.readLine());
            list.add(new Gadget.GadgetBuilder()
                    .setItemId(line)
                    .setItemName(itemName)
                    .setItemPrice(itemPrice)
                    .setItemQuantity(itemQuantity)
                    .setItemWarrantyInMonths(itemWarrantyInMonths)
                    .createGadget());
        }
        return list;
    }
    public static void addNewGadgetItem(List<Gadget>list) throws IOException {
        FileWriter fileWriter = new FileWriter("files/gadgets.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (Gadget gadget : list) {
            printWriter.println(gadget.getItemId());
            printWriter.println(gadget.getItemName());
            printWriter.println(gadget.getItemPrice());
            printWriter.println(gadget.getItemQuantity());
            printWriter.println(gadget.getItemWarrantyInMonths());
        }
        printWriter.flush();
    }

    public static List<Food> getFoodItems() throws IOException {
        List<Food> list = new ArrayList<>();
        FileReader fileReader = new FileReader("files/foods.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String itemName = bufferedReader.readLine();
            BigDecimal itemPrice = new BigDecimal(bufferedReader.readLine());
            Integer itemQuantity = Integer.valueOf(bufferedReader.readLine());
            Integer amountOfProteins = Integer.valueOf(bufferedReader.readLine());
            Integer amountOfCarbohydrates = Integer.valueOf(bufferedReader.readLine());
            Integer amountOfFats = Integer.valueOf(bufferedReader.readLine());
            list.add(new Food.FoodBuilder()
                    .setItemId(line)
                    .setItemName(itemName)
                    .setItemPrice(itemPrice)
                    .setItemQuantity(itemQuantity)
                    .setNutritionalValue(new NutritionalValue.NutritionalValueBuilder()
                            .setAmountOfProteins(amountOfProteins)
                            .setAmountOfCarbohydrates(amountOfCarbohydrates)
                            .setAmountOfFats(amountOfFats)
                            .createNutritionalValue()).
                    createFood());
        }
        return list;
    }

    public static List<User> getUsers() throws IOException {
        List<User> list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader("files/users.txt"));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String userName = bufferedReader.readLine();
            String userPassword = bufferedReader.readLine();
            String roleString = bufferedReader.readLine();
            Roles role = null;
            for (Roles roles : Roles.values())
                if (roleString.equals(roles.getRole()))
                    role = roles;
            list.add(new User.UserBuilder()
                    .setItemId(line)
                    .setUserName(userName)
                    .setUserPassword(userPassword)
                    .setRole(role)
                    .createUser());
        }
        return list;


    }
    public static void addNewFoodItem(List<Food>list) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter("files/foods.txt"));
        for (Food food : list) {
            printWriter.println(food.getItemId());
            printWriter.println(food.getItemName());
            printWriter.println(food.getItemPrice());
            printWriter.println(food.getItemQuantity());
            printWriter.println(food.getNutritionalValue().getAmountOfProtein());
            printWriter.println(food.getNutritionalValue().getAmountOfCarbohydrate());
            printWriter.println(food.getNutritionalValue().getAmountOfFat());
        }
        printWriter.flush();
    }

    public static void addNewUser(List<User> list) throws IOException {
        FileWriter fileWriter = new FileWriter("files/users.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (User user : list) {
            printWriter.println(user.getItemId());
            printWriter.println(user.getUserName());
            printWriter.println(user.getUserPassword());
            printWriter.println(user.getRole());
        }
        printWriter.flush();
    }
}
