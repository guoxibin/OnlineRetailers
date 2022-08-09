package com.example.onlineretailers.ui.purchasepage.bean;

public class ShoppingNameAndCount {

    private String shoppingName;//商品名
    private int shoppingCount; //商品数量


    public ShoppingNameAndCount(String shoppingName, int shoppingCount) {
        this.shoppingName = shoppingName;
        this.shoppingCount = shoppingCount;
    }

    public String getShoppingName() {
        return shoppingName;
    }

    public int getShoppingCount() {
        return shoppingCount;
    }
}
