package com.example.javaproject.sorters;

import com.example.javaproject.entities.Food;

import java.util.Comparator;

public class SortingFoods implements Comparator<Food> {
    @Override
    public int compare(Food o1, Food o2) {
        int t = o2.getItemPrice().compareTo(o1.getItemPrice());
        return t == 0 ? o1.getItemName().compareTo(o2.getItemName()) : t;
    }
}
