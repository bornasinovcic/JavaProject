package com.example.javaproject.entities;

import java.io.Serializable;

public abstract class Id implements Serializable {
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
