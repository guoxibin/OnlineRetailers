package com.example.onlineretailers.ui.cart;

import android.os.Handler;

import com.example.onlineretailers.roomDatabase.Item;
import com.example.onlineretailers.ui.cart.bean.ShoppingItem;

import java.util.List;

public interface CartContact {

    interface CartDetailPresent {
        void getDetailData();
        void setDeleteShoppingItem(List<ShoppingItem> list, Handler handler);
        void setStoreIntoDatabase(List<ShoppingItem> list, Handler handler);
    }


    interface CartDetailView {
        void getSuccessOrdersInformation(List<Item> list);
    }

    interface CartDetailModel {
        List<Item> getDetailDataForDatabase();
        void setDeleteShoppingItem(List<ShoppingItem> list, Handler handler);
        void setStoreIntoDatabase(List<ShoppingItem> shoppingItemList, Handler handler);
    }
}
