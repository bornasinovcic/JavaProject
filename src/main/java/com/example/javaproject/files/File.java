package com.example.javaproject.files;

import com.example.javaproject.entities.Food;
import com.example.javaproject.entities.NutritionalValue;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class File {
    public static List<Food> getFoodItems() throws IOException {
        List<Food> list = new ArrayList<>();
        FileReader fileReader = new FileReader("files/foods.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String itemId = line;
            String itemName = bufferedReader.readLine();
            BigDecimal itemPrice = new BigDecimal(bufferedReader.readLine());
            Integer itemQuantity = Integer.valueOf(bufferedReader.readLine());
            Integer amountOfProteins = Integer.valueOf(bufferedReader.readLine());
            Integer amountOfCarbohydrates = Integer.valueOf(bufferedReader.readLine());
            Integer amountOfFats = Integer.valueOf(bufferedReader.readLine());
            list.add(new Food(itemId, itemName, itemPrice, itemQuantity, new NutritionalValue(amountOfProteins, amountOfCarbohydrates, amountOfFats)));
        }
        return list;
    }
    public static void addNewFoodItem(List<Food>list) throws IOException {
        FileWriter fileWriter = new FileWriter("files/foods.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
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
}
