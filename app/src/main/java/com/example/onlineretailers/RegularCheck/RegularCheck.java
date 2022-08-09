package com.example.onlineretailers.RegularCheck;

public class RegularCheck {
    public static final String USERNAME_PATTERN
            = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";//用户名的正则校验
    public static final String PASSWORD_PATTERN
            = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z\\W]{6,18}$";//密码的正则校验
}
