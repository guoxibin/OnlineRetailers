/**
 * Copyright 2021 bejson.com
 */
package com.example.onlineretailers.Bean;


import androidx.annotation.NonNull;

/**
 *
 * @param <T>
 */
public class BaseBean<T> {

    private int code;
    private String message;
    private T data;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }


    @NonNull
    @Override
    public String toString() {
        return "BaseBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}