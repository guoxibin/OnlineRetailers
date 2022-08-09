package com.example.onlineretailers.ui.purchasepage.bean;

public class PurchaseShopping {
    private String username;//用户名
    private String name;//商品名称
    private int purchaseCount;//购买数量
    private String totalPrice;//付款金额
    private String imageUrl;//图片地址


    public PurchaseShopping(String username, String name, int purchaseCount, String totalPrice, String imageUrl) {
        this.username = username;
        this.name = name;
        this.purchaseCount = purchaseCount;
        this.totalPrice = totalPrice;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPurchaseCount() {
        return purchaseCount;
    }

    public void setPurchaseCount(int purchaseCount) {
        this.purchaseCount = purchaseCount;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
