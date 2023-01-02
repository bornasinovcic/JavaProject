package com.example.javaproject;

import com.example.javaproject.entities.Food;
import com.example.javaproject.entities.Gadget;
import com.example.javaproject.entities.Item;
import com.example.javaproject.entities.NutritionalValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestingMain {
    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        items.add(new Food(1L, "Banana", new BigDecimal("0.05"), 11, new NutritionalValue(1, 28, 0)));
        items.add(new Gadget(2L, "iPhone 14 Pro Max", new BigDecimal("1599.00"), 1, 60));
        items.add(new Food(3L, "Milk", new BigDecimal("1.08"), 2, new NutritionalValue(8, 11, 8)));
        items.add(new Gadget(4L, "SAMSUNG Galaxy Z Fold", new BigDecimal("1857.99"), 2, 60));
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
        BigDecimal total = BigDecimal.ZERO;
        for (Item value : items) {
            total = total.add(value.getTotalPriceOfItem());
        }
        System.out.println("total -> " + total);
//        example 2
        total = BigDecimal.ZERO;
        System.out.println("total -> " + total);
        total = items.stream()
                .map(item -> item.getItemPrice().multiply(BigDecimal.valueOf(item.getItemQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("total -> " + total);
//        example 3
        total = BigDecimal.ZERO;
        System.out.println("total -> " + total);
        total = items.stream()
                .map(Item::getTotalPriceOfItem)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("total -> " + total);
    }
}