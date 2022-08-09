package com.example.onlineretailers.ui.purchasepage;

import com.example.onlineretailers.roomDatabase.PrePurchase;

import java.util.List;

public interface PurchasePageContact {
    interface PurchasePageView {
        void getPurchaseInformation(List<PrePurchase> prePurchasesList);
    }

    interface PurchasePagePresenter {
        void getPurchaseInformation();
    }

    interface PurchasePageModel {
        List<PrePurchase> getPurchaseInformation();
    }
}
