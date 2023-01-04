package com.example.javaproject.entities;

import java.util.Comparator;

public class ItemComparator implements Comparator<Item> {
    @Override
    public int compare(Item i1, Item i2) {
        int t = i2.getItemPrice().compareTo(i1.getItemPrice());
        t = t == 0 ? i1.getItemName().compareTo(i2.getItemName()) : t;
        return t == 0 ? i2.getItemQuantity().compareTo(i1.getItemQuantity()) : t;
    }
}
