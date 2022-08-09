/**
 * Copyright 2021 bejson.com
 */
package com.example.onlineretailers.Bean;

import androidx.annotation.NonNull;

import java.util.List;

/**
 * 商品内容
 */
public class Goods {

    private int goodsId;
    private int spanSize;
    private List<String> banners;
    private String imageUrl;
    private String text;

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setBanners(List<String> banners) {
        this.banners = banners;
    }

    public List<String> getBanners() {
        return banners;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @NonNull
    @Override
    public String toString() {
        return "Goods{" +
                "goodsId=" + goodsId +
                ", spanSize=" + spanSize +
                ", banners=" + banners +
                ", imageUrl='" + imageUrl + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}