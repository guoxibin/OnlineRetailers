package com.example.onlineretailers.roomDatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "PersonnelInformation")
public class PersonnelInformation {

    @PrimaryKey(autoGenerate = true)
    public int uid;//购物id(自增)

    @ColumnInfo(name = "username",typeAffinity = ColumnInfo.TEXT)
    public String username;//用户id

    @ColumnInfo(name = "name", typeAffinity = ColumnInfo.TEXT)
    public String personName;//人名

    @ColumnInfo(name = "password", typeAffinity = ColumnInfo.TEXT)
    public String password;//密码

    @ColumnInfo(name = "phoneNumber", typeAffinity = ColumnInfo.TEXT)
    public String phoneNumber;//手机号码

    @ColumnInfo(name = "loginState", typeAffinity = ColumnInfo.INTEGER)
    public int loginState;//登录状态（登陆中：1, 否则0）

    @ColumnInfo(name = "smallChange", typeAffinity = ColumnInfo.TEXT)
    public String smallChange;//个人零钱

    public PersonnelInformation(String username, String personName, String password,
                                String phoneNumber, int loginState, String smallChange) {
        this.username = username;
        this.personName = personName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.loginState = loginState;
        this.smallChange = smallChange;
    }

    public String getSmallChange() {
        return smallChange;
    }

    public void setSmallChange(String smallChange) {
        this.smallChange = smallChange;
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

    public void setUsername(String personId) {
        this.username = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getLoginState() {
        return loginState;
    }

    public void setLoginState(int loginState) {
        this.loginState = loginState;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
