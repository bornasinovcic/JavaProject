package com.example.javaproject.entities;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemId itemId1 = (ItemId) o;
        return Objects.equals(itemId, itemId1.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId);
    }
}
