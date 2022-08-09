package com.example.onlineretailers.roomDatabase;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HistoricalOrdersDao {

    @Insert
    void insert(HistoricalOrders historicalOrders);

    @Query("SELECT * FROM HistoricalOrders WHERE username =:username")
    List<HistoricalOrders> getHistoricalOrdersList(String username);
}
