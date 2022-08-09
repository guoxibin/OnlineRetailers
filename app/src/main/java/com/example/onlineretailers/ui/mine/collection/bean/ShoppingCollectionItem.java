package com.example.onlineretailers.ui.mine.collection.bean;

public class ShoppingCollectionItem {

    private String shoppingName;//商品名
    private String shoppingUnitPrice;//商品单价
    private String collectionTime;//收藏时间

    public ShoppingCollectionItem(String shoppingName, String shoppingUnitPrice, String collectionTime) {
        this.shoppingName = shoppingName;
        this.shoppingUnitPrice = shoppingUnitPrice;
        this.collectionTime = collectionTime;
    }

    public String getShoppingName() {
        return shoppingName;
    }

    public String getShoppingUnitPrice() {
        return shoppingUnitPrice;
    }

    public String getCollectionTime() {
        return collectionTime;
    }
}
