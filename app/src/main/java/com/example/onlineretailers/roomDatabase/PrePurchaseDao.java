package com.example.onlineretailers.roomDatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PrePurchaseDao {
    @Insert
    void insertPrePurchase(PrePurchase prePurchase);

    @Query("SELECT * FROM PrePurchase")
    List<PrePurchase> getPrePurchaseList();


    @Query("DELETE FROM PrePurchase")
    void deleteAll();
}
