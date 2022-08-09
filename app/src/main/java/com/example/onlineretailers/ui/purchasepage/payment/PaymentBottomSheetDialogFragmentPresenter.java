package com.example.onlineretailers.ui.purchasepage.payment;

import android.util.Log;

import com.example.onlineretailers.ui.purchasepage.bean.PurchaseShopping;

import java.util.List;

public class PaymentBottomSheetDialogFragmentPresenter implements PaymentBottomSheetDialogContact
        .PaymentBottomSheetDialogFragmentPresenter{

    private PaymentBottomSheetDialogFragmentModel paymentBottomSheetDialogFragmentModel;

    public PaymentBottomSheetDialogFragmentPresenter() {
        paymentBottomSheetDialogFragmentModel = new PaymentBottomSheetDialogFragmentModel();
    }

    @Override
    public void setDeletePrePurchase() {
        paymentBottomSheetDialogFragmentModel.setDeletePrePurchase();
    }

    @Override
    public boolean setEnoughPayment(String totalPrice) {
        return paymentBottomSheetDialogFragmentModel.setEnoughPayment(totalPrice);
    }

    @Override
    public void setInsertHistoricalOrders(String totalPrice, List<PurchaseShopping> purchaseShoppingList) {
        paymentBottomSheetDialogFragmentModel.setInsertHistoricalOrders(totalPrice, purchaseShoppingList);
    }
}
