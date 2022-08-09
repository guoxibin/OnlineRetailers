package com.example.onlineretailers.roomDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CollectionsDao {

    @Insert
    void insert(Collections collections);

    @Delete
    void delete(Collections collections);

    @Update
    void updateItem(Item item);

    @Query("SELECT * FROM Collections WHERE username =:username")
    List<Collections> getCollectionsList(String username);

    @Query("SELECT name FROM Collections WHERE username =:username")
    List<String> getCollectionsShoppingName(String username);
}
