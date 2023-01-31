package com.example.javaproject.entities;

import java.math.BigDecimal;

public class Gadget extends Item {
    private Integer itemWarrantyInMonths;
    public Gadget(String itemId, String itemName, BigDecimal itemPrice, Integer itemQuantity, Integer itemWarrantyInMonths) {
        super(itemId, itemName, itemPrice, itemQuantity);
        this.itemWarrantyInMonths = itemWarrantyInMonths;
    }


    public Integer getItemWarrantyInMonths() {
        return itemWarrantyInMonths;
    }

    public void setItemWarrantyInMonths(Integer itemWarrantyInMonths) {
        this.itemWarrantyInMonths = itemWarrantyInMonths;
    }

    public static class GadgetBuilder {
        private String itemId;
        private String itemName;
        private BigDecimal itemPrice;
        private Integer itemQuantity;
        private Integer itemWarrantyInMonths;

        public GadgetBuilder setItemId(String itemId) {
            this.itemId = itemId;
            return this;
        }

        public GadgetBuilder setItemName(String itemName) {
            this.itemName = itemName;
            return this;
        }

        public GadgetBuilder setItemPrice(BigDecimal itemPrice) {
            this.itemPrice = itemPrice;
            return this;
        }

        public GadgetBuilder setItemQuantity(Integer itemQuantity) {
            this.itemQuantity = itemQuantity;
            return this;
        }

        public GadgetBuilder setItemWarrantyInMonths(Integer itemWarrantyInMonths) {
            this.itemWarrantyInMonths = itemWarrantyInMonths;
            return this;
        }

        public Gadget createGadget() {
            return new Gadget(itemId, itemName, itemPrice, itemQuantity, itemWarrantyInMonths);
        }
    }
}
