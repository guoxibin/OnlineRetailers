package com.example.onlineretailers.roomDatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 预购买列表(登录状态均为已登录状态用户的商品)
 */
@Entity(tableName = "PrePurchase")
public class PrePurchase {

    @PrimaryKey(autoGenerate = true)
    private int uid;//购物id(自增)

    @ColumnInfo(name = "username",typeAffinity = ColumnInfo.TEXT)
    private String username;//用户名

    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
    private String name;//商品名

    @ColumnInfo(name = "quantity", typeAffinity = ColumnInfo.INTEGER)
    private int quantity;//购买数量

    @ColumnInfo(name = "personName", typeAffinity = ColumnInfo.TEXT)
    private String personName;//真实姓名

    @ColumnInfo(name = "phoneNumber", typeAffinity = ColumnInfo.TEXT)
    private String phoneNumber;//手机号码

    @ColumnInfo(name = "totalPrice", typeAffinity =  ColumnInfo.TEXT)
    private String totalPrice;//总价

    @ColumnInfo(name = "imageUrl", typeAffinity = ColumnInfo.TEXT)
    private String imageUrl;//照片地址



    public PrePurchase(String username, String name, int quantity, String personName,
                       String phoneNumber, String totalPrice, String imageUrl) {
        this.username = username;
        this.name = name;
        this.quantity = quantity;
        this.personName = personName;
        this.phoneNumber = phoneNumber;
        this.totalPrice = totalPrice;
        this.imageUrl = imageUrl;
    }


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

}
