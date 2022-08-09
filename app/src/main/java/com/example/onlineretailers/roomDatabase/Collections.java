package com.example.onlineretailers.roomDatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 收藏列表
 */
@Entity(tableName = "Collections")
public class Collections {

    @PrimaryKey(autoGenerate = true)
    public int uid;//购物id(自增)

    @ColumnInfo(name = "username",typeAffinity = ColumnInfo.TEXT)
    private String username;//用户id

    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
    private String name;//商品名

    @ColumnInfo(name = "unitPrice", typeAffinity = ColumnInfo.TEXT)
    private String unitPrice;//商品单价

    @ColumnInfo(name = "collectionTime", typeAffinity = ColumnInfo.TEXT)
    private String collectionTime;//收藏时间

    public Collections(String username, String name, String unitPrice, String collectionTime) {
        this.username = username;
        this.name = name;
        this.unitPrice = unitPrice;
        this.collectionTime = collectionTime;
    }

    public String getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(String collectionTime) {
        this.collectionTime = collectionTime;
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

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
