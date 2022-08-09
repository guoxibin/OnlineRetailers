package com.example.onlineretailers.ui.purchasepage;

import com.example.onlineretailers.roomDatabase.PrePurchase;
import com.example.onlineretailers.roomDatabase.ShoppingItemDatabase;
import com.example.onlineretailers.roomDatabase.ThreadPool;
import com.example.onlineretailers.ui.context.ApplicationContext;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class PurchasePageModel implements PurchasePageContact.PurchasePageModel{
    private ShoppingItemDatabase shoppingItemDatabase;
    private List<PrePurchase> prePurchaseList;

    @Override
    public List<PrePurchase> getPurchaseInformation() {
        if (shoppingItemDatabase == null) {
            shoppingItemDatabase = ShoppingItemDatabase
                    .getInstance(ApplicationContext.getContext());
        }
        Future<List<PrePurchase>>  future = ThreadPool.threadPoolExecutor.submit(() ->
                shoppingItemDatabase.prePurchaseDao().getPrePurchaseList());
        try {
            prePurchaseList = future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return prePurchaseList;
    }
}
