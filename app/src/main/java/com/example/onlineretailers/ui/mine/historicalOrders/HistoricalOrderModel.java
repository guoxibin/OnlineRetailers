package com.example.onlineretailers.ui.mine.historicalOrders;

import com.example.onlineretailers.roomDatabase.HistoricalOrders;
import com.example.onlineretailers.roomDatabase.ShoppingItemDatabase;
import com.example.onlineretailers.roomDatabase.ThreadPool;
import com.example.onlineretailers.ui.context.ApplicationContext;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class HistoricalOrderModel implements HistoricalOrderContact.HistoricalOrderModel{
    private List<HistoricalOrders> historicalOrdersList;
    private ShoppingItemDatabase shoppingItemDatabase;

    @Override
    public List<HistoricalOrders> getSuccessHistoricalData() {
        shoppingItemDatabase = ShoppingItemDatabase
                .getInstance(ApplicationContext.getContext());

        Future<List<HistoricalOrders>> future = ThreadPool.threadPoolExecutor.submit(() -> {
            String username = shoppingItemDatabase.personnelInformationDao().getLoggingUsername();
            return shoppingItemDatabase.historicalOrdersDao().getHistoricalOrdersList(username);
        });

        try {
            historicalOrdersList = future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return historicalOrdersList;
    }
}
