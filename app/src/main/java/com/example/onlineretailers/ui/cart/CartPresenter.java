package com.example.onlineretailers.ui.cart;

import android.os.Handler;

import com.example.onlineretailers.roomDatabase.Item;
import com.example.onlineretailers.ui.cart.bean.ShoppingItem;

import java.util.List;

public class CartPresenter implements CartContact.CartDetailPresent{

    private CartContact.CartDetailView cartDetailView;
    private CartModel cartModel;


    public CartPresenter(CartContact.CartDetailView cartDetailView) {
        this.cartDetailView = cartDetailView;
        cartModel = new CartModel();
    }


    @Override
    public void getDetailData() {
        List<Item> list = cartModel.getDetailDataForDatabase();
        cartDetailView.getSuccessOrdersInformation(list);
    }

    @Override
    public void setDeleteShoppingItem(List<ShoppingItem> list, Handler handler) {
        cartModel.setDeleteShoppingItem(list, handler);
    }

    @Override
    public void setStoreIntoDatabase(List<ShoppingItem> list, Handler handler) {
        cartModel.setStoreIntoDatabase(list, handler);
    }

}
