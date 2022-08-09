package com.example.onlineretailers.ui.mine.historicalOrders.bean;

public class HistoricalOrdersItem {

    private String shoppingName; //商品名
    private int shoppingCount; //商品数量
    private String purchaseTime; //购买时间
    private String totalPrice; //总价


    public HistoricalOrdersItem(String shoppingName, int shoppingCount, String purchaseTime, String totalPrice) {
        this.shoppingName = shoppingName;
        this.shoppingCount = shoppingCount;
        this.purchaseTime = purchaseTime;
        this.totalPrice = totalPrice;
    }


    public String getShoppingName() {
        return shoppingName;
    }

    public int getShoppingCount() {
        return shoppingCount;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public String getTotalPrice() {
        return totalPrice;
    }
}
