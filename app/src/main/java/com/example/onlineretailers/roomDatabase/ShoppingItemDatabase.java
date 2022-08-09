package com.example.onlineretailers.roomDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Item.class, PersonnelInformation.class, PrePurchase.class,
        Collections.class, HistoricalOrders.class}, version = 1)
public abstract class ShoppingItemDatabase extends RoomDatabase {
    private static ShoppingItemDatabase shoppingItemDatabase;

    public static synchronized ShoppingItemDatabase getInstance(Context context) {
        if (shoppingItemDatabase == null) {
            shoppingItemDatabase = Room
                    .databaseBuilder(context.getApplicationContext(), ShoppingItemDatabase.class,
                            "my_db").build();
        }

        return shoppingItemDatabase;
    }

    public abstract ItemDao itemDao();

    public abstract PersonnelInformationDao personnelInformationDao();

    public abstract PrePurchaseDao prePurchaseDao();

    public abstract CollectionsDao collectionsDao();

    public abstract HistoricalOrdersDao historicalOrdersDao();
}
