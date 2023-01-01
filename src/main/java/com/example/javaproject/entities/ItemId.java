package com.example.javaproject.entities;

public abstract class ItemId {
    private Long itemId;

    public ItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}
