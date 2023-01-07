package com.example.javaproject.sorters;

import com.example.javaproject.entities.Gadget;

import java.util.Comparator;

public class SortingGadgets implements Comparator<Gadget> {
    @Override
    public int compare(Gadget o1, Gadget o2) {
        int t = o2.getItemWarrantyInMonths().compareTo(o1.getItemWarrantyInMonths());
        return t == 0 ? o1.getItemName().compareTo(o2.getItemName()) : t;
    }
}
