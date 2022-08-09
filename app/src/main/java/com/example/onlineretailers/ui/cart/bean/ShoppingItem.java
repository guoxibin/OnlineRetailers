package com.example.onlineretailers.ui.cart.bean;


/**
 * 购物车子项布局
 * imageView：商品照片
 * textView:商品名 + 商品数量 + 商品价格
 */
public class ShoppingItem {

    private String goodsOrderImage;
    private String goodsOrderText;
    private int uid;//商品id
    private boolean isCheck;//是否被选中

    public String getGoodsOrderImage() {
        return goodsOrderImage;
    }

    public ShoppingItem(int uid, String image, String text, boolean isCheck) {
        this.uid = uid;
        this.goodsOrderImage = image;
        this.goodsOrderText = text;
        this.isCheck = isCheck;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getgoodsOrderImage() {
        return goodsOrderImage;
    }

    public void setgoodsOrderImage(String goodsOrderImage) {
        this.goodsOrderImage = goodsOrderImage;
    }

    public String getgoodsOrderText() {
        return goodsOrderText;
    }

    public void setgoodsOrderText(String goodsOrderText) {
        this.goodsOrderText = goodsOrderText;
    }
}
