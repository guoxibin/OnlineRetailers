package com.example.onlineretailers.ui.mine.historicalOrders;

public class HistoricalOrderPresenter implements HistoricalOrderContact.HistoricalOrderPresenter{

    private final HistoricalOrderContact.HistoricalOrderView historicalOrderView;
    private final HistoricalOrderContact.HistoricalOrderModel historicalOrderModel;

    public HistoricalOrderPresenter(HistoricalOrderContact.HistoricalOrderView historicalOrderView) {
        this.historicalOrderView = historicalOrderView;
        historicalOrderModel = new HistoricalOrderModel();
    }

    @Override
    public void getSuccessHistoricalData() {
        historicalOrderView.getSuccessHistoricalData(historicalOrderModel.getSuccessHistoricalData());
    }
}
