package com.example.javaproject.entities;

public abstract class ItemId {
    private String itemId;

    public ItemId(String itemId) {
        this.itemId = itemId;
    }


    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

}
