package com.example.javaproject;

import com.example.javaproject.entities.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestingMain {
    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        items.add(new Gadget("fkVjhvCBJHvV6Zwn", "iPhone 14 Pro Max", new BigDecimal("1599.00"), 1, 60));
        items.add(new Food("wTSHrg4BaKTyFvRe", "Banana", new BigDecimal("0.05"), 11, new NutritionalValue(1, 28, 0)));
        items.add(new Food("F6ZNt2WmEx3YZfek", "Milm", new BigDecimal("1.08"), 1, new NutritionalValue(8, 11, 8)));
        items.add(new Food("F6ZNt2WmEx3YZfek", "Milm", new BigDecimal("1.08"), 5, new NutritionalValue(8, 11, 8)));
        items.add(new Gadget("fkVjhvCBJHvV6Zwn", "iPhone 14 Pro Max", new BigDecimal("1599.00"), 1, 60));
        items.add(new Food("AXXtMMSxzznR5pzG", "Milk", new BigDecimal("1.08"), 2, new NutritionalValue(8, 11, 8)));
        items.add(new Food("AXXtMMSxzznR5pzG", "Milk", new BigDecimal("2.08"), 2, new NutritionalValue(8, 11, 8)));
        items.add(new Gadget("wT4v5S2aVAvBaSra", "SAMSUNG Galaxy Z Fold", new BigDecimal("1857.99"), 2, 60));
        items.add(new Gadget("fkVjhvCBJHvV6Zwn", "iPhone 14 Pro Max", new BigDecimal("1599.00"), 1, 60));
        for (Item item : items) {
            if (item instanceof Food food)
                System.out.println("[" +
                        food.getItemId() + "] -> [" +
                        food.getItemName() + "] -> [" +
                        food.getItemPrice() + "] -> [" +
                        food.getItemQuantity() + "] -> [" +
                        food.getTotalPriceOfItem() + "] -> [" +
                        food.getNutritionalValue().getFinalCaloricValueOfItem() + "] -> [" +
                        food.getCaloricValueOfAllItems() + "]");
            if (item instanceof Gadget gadget) {
                System.out.println("[" +
                        gadget.getItemId() + "] -> [" +
                        gadget.getItemName() + "] -> [" +
                        gadget.getItemPrice() + "] -> [" +
                        gadget.getItemQuantity() + "] -> [" +
                        gadget.getTotalPriceOfItem() + "] -> [" +
                        gadget.getItemWarrantyInMonths() + "]");
            }
        }
//        example 1
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Item value : items) {
            totalPrice = totalPrice.add(value.getTotalPriceOfItem());
        }
        System.out.println("totalPrice -> " + totalPrice);
//        example 2
        totalPrice = BigDecimal.ZERO;
        System.out.println("totalPrice -> " + totalPrice);
        totalPrice = items.stream()
                .map(item -> item.getItemPrice().multiply(BigDecimal.valueOf(item.getItemQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("totalPrice -> " + totalPrice);
//        example 3
        totalPrice = BigDecimal.ZERO;
        System.out.println("totalPrice -> " + totalPrice);
        totalPrice = items.stream()
                .map(Item::getTotalPriceOfItem)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("totalPrice -> " + totalPrice);
        List<Food> foodList = new ArrayList<>();
        List<Gadget> gadgetList = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Food food) {
                try {
                    if (foodList.size() != 0) duplicateFood(foodList, food);
                    foodList.add(food);
                } catch (DuplicateItem e) {
                    System.out.println(e.getMessage());
                }
            }
            if (item instanceof Gadget gadget) {
                try {
                    if (gadgetList.size() != 0) duplicateGadget(gadgetList, gadget);
                    gadgetList.add(gadget);
                } catch (DuplicateItem e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        foodList.forEach(food -> System.out.println("food -> [" +
                food.getItemId() + "] -> [" +
                food.getItemName() + "] -> [" +
                food.getItemPrice() + "] -> [" +
                food.getItemQuantity() + "] -> [" +
                food.getTotalPriceOfItem() + "] -> [" +
                food.getNutritionalValue().getFinalCaloricValueOfItem() + "] -> [" +
                food.getCaloricValueOfAllItems() + "]"));
        gadgetList.forEach(gadget -> System.out.println("gadget -> [" +
                gadget.getItemId() + "] -> [" +
                gadget.getItemName() + "] -> [" +
                gadget.getItemPrice() + "] -> [" +
                gadget.getItemQuantity() + "] -> [" +
                gadget.getTotalPriceOfItem() + "] -> [" +
                gadget.getItemWarrantyInMonths() + "]"));
        foodList = foodList.stream().sorted(new ItemComparator()).toList();
        gadgetList = gadgetList.stream().sorted(new ItemComparator()).toList();
        System.out.println("SORTED");
        foodList.forEach(food -> System.out.println("food -> [" +
                food.getItemId() + "] -> [" +
                food.getItemName() + "] price -> [" +
                food.getItemPrice() + "] -> [" +
                food.getItemQuantity() + "] -> [" +
                food.getTotalPriceOfItem() + "] -> [" +
                food.getNutritionalValue().getFinalCaloricValueOfItem() + "] -> [" +
                food.getCaloricValueOfAllItems() + "]"));
        gadgetList.forEach(gadget -> System.out.println("gadget -> [" +
                gadget.getItemId() + "] -> [" +
                gadget.getItemName() + "] price -> [" +
                gadget.getItemPrice() + "] -> [" +
                gadget.getItemQuantity() + "] -> [" +
                gadget.getTotalPriceOfItem() + "] -> [" +
                gadget.getItemWarrantyInMonths() + "]"));
    }

    private static void duplicateFood(List<Food> foodList, Food food1) throws DuplicateItem {
        for (Food food2 : foodList)
            if (food2.getItemId().equals(food1.getItemId()))
                throw new DuplicateItem("Duplicate food");
    }
    private static void duplicateGadget(List<Gadget> foodList, Gadget gadget1) throws DuplicateItem {
        for (Gadget gadget2 : foodList)
            if (gadget2.getItemId().equals(gadget1.getItemId()))
                throw new DuplicateItem("Duplicate gadget");
    }
}