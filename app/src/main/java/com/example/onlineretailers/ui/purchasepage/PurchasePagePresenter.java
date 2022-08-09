package com.example.onlineretailers.ui.purchasepage;

public class PurchasePagePresenter implements PurchasePageContact.PurchasePagePresenter{

    private PurchasePageContact.PurchasePageView purchasePageView;
    private PurchasePageModel purchasePageModel;

    public PurchasePagePresenter(PurchasePageContact.PurchasePageView purchasePageView) {
        this.purchasePageView = purchasePageView;
        purchasePageModel = new PurchasePageModel();
    }

    @Override
    public void getPurchaseInformation() {
        purchasePageView.getPurchaseInformation(purchasePageModel.getPurchaseInformation());
    }
}
