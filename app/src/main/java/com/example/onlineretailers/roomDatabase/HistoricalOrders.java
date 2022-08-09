package com.example.onlineretailers.roomDatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 历史订单列表（已支付）
 */
@Entity(tableName = "HistoricalOrders")
public class HistoricalOrders {

    @PrimaryKey(autoGenerate = true)
    public int uid;//购物id(自增)

    @ColumnInfo(name = "username",typeAffinity = ColumnInfo.TEXT)
    private String username;//用户id

    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
    private String name;//商品名

    @ColumnInfo(name = "quantity", typeAffinity = ColumnInfo.INTEGER)
    private int quantity;//购买数量

    @ColumnInfo(name = "totalPrice", typeAffinity = ColumnInfo.TEXT)
    private String totalPrice;//商品总价

    @ColumnInfo(name = "purchaseTime", typeAffinity = ColumnInfo.TEXT)
    private String purchaseTime;//收藏时间

    public HistoricalOrders(String username, String name, int quantity, String totalPrice, String purchaseTime) {
        this.username = username;
        this.name = name;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.purchaseTime = purchaseTime;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCollectionTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }
}
