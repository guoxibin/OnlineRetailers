package com.example.onlineretailers.ui.mine.historicalOrders;

import com.example.onlineretailers.roomDatabase.HistoricalOrders;

import java.util.List;

public interface HistoricalOrderContact {
    interface HistoricalOrderView {
        void getSuccessHistoricalData(List<HistoricalOrders> historicalOrdersList);
    }

    interface HistoricalOrderPresenter {
        void getSuccessHistoricalData();
    }

    interface HistoricalOrderModel {
        List<HistoricalOrders> getSuccessHistoricalData();
    }
}
