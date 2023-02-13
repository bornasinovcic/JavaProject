package com.example.javaproject.sorters;

import com.example.javaproject.entities.Item;
import com.example.javaproject.generics.Changes;

import java.util.Comparator;

public class SortingChanges implements Comparator<Changes<Item>> {
    @Override
    public int compare(Changes<Item> o1, Changes<Item> o2) {
        return o2.getLocalDateTime().compareTo(o1.getLocalDateTime());
    }
}
