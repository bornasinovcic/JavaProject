package com.example.javaproject.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Item extends ItemId {
    private String itemName;
    private BigDecimal itemPrice;
    private Integer itemQuantity;

    public Item(String itemId, String itemName, BigDecimal itemPrice, Integer itemQuantity) {
        super(itemId);
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
    }
    public BigDecimal getTotalPriceOfItem() {
        return itemPrice.multiply(BigDecimal.valueOf(itemQuantity)).setScale(2, RoundingMode.HALF_UP);
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Item item = (Item) o;
        return Objects.equals(itemName, item.itemName) && Objects.equals(itemPrice, item.itemPrice) && Objects.equals(itemQuantity, item.itemQuantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), itemName, itemPrice, itemQuantity);
    }
}
