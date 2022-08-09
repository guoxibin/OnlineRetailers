package com.example.onlineretailers.roomDatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


/**
 * 购物车列表
 */
@Entity(tableName = "Item")
public class Item {

    @PrimaryKey(autoGenerate = true)
    public int uid;//购物id(自增)

    @ColumnInfo(name = "purchase",typeAffinity = ColumnInfo.TEXT)
    public String purchase;//购主

    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
    public String name;//商品名

    @ColumnInfo(name = "unitPrice", typeAffinity = ColumnInfo.TEXT)
    public String unitPrice;//商品单价

    @ColumnInfo(name = "quantity", typeAffinity = ColumnInfo.INTEGER)
    public int quantity;//购买数量

    @ColumnInfo(name = "totalPrice", typeAffinity =  ColumnInfo.TEXT)
    public String totalPrice;//总价

    @ColumnInfo(name = "imageUrl", typeAffinity = ColumnInfo.TEXT)
    public String imageUrl;//照片地址

    @ColumnInfo(name = "purchaseTime", typeAffinity = ColumnInfo.TEXT)
    public String purchaseTime;//购买时间

    @Ignore
    public Item(String name, String unitPrice, int quantity, String totalPrice, String imageUrl, String purchaseTime) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.imageUrl = imageUrl;
        this.purchaseTime = purchaseTime;
    }

    public Item(String purchase, String name, String unitPrice, int quantity, String totalPrice, String imageUrl, String purchaseTime) {
        this.purchase = purchase;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.imageUrl = imageUrl;
        this.purchaseTime = purchaseTime;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPurchase() {
        return purchase;
    }

    public void setPurchase(String purchase) {
        this.purchase = purchase;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }
}
