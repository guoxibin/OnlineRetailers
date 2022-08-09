package com.example.onlineretailers.roomDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ItemDao {
    @Insert
    void insertItem(Item item);

    @Delete
    void deleteItem(Item item);

    @Query("DELETE FROM Item WHERE uid =:uid")
    void deleteByUid(int uid);

    @Update
    void updateItem(Item item);

    @Query("SELECT * FROM Item")
    List<Item> getItemList();

    @Query("SELECT * FROM Item WHERE purchase =:username")
    List<Item> getLoginItemList(String username);

    @Query("SELECT * FROM Item WHERE uid =:uid")
    Item getItemByUid(int uid);
}
