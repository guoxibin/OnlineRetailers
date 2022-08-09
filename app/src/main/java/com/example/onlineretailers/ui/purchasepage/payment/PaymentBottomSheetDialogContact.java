package com.example.onlineretailers.ui.purchasepage.payment;

import com.example.onlineretailers.ui.purchasepage.bean.PurchaseShopping;

import java.util.List;

public interface PaymentBottomSheetDialogContact {
    interface PaymentBottomSheetDialogFragmentPresenter {
        void setDeletePrePurchase();
        boolean setEnoughPayment(String totalPrice);

        void setInsertHistoricalOrders(String totalPrice, List<PurchaseShopping> purchaseShoppingList);
    }

    interface PaymentBottomSheetDialogFragmentModel {
        void setDeletePrePurchase();
        boolean setEnoughPayment(String totalPrice);
        void setInsertHistoricalOrders(String totalPrice, List<PurchaseShopping> purchaseShoppingList);

    }
}
