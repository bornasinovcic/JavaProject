package com.example.javaproject.entities;

public abstract class Id {
    private String itemId;

    public Id(String itemId) {
        this.itemId = itemId;
    }


    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

}
