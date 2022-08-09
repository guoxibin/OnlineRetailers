package com.example.onlineretailers.ui.mine.collection;

import com.example.onlineretailers.roomDatabase.Collections;
import com.example.onlineretailers.roomDatabase.ShoppingItemDatabase;
import com.example.onlineretailers.roomDatabase.ThreadPool;
import com.example.onlineretailers.ui.context.ApplicationContext;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CollectionModel implements CollectionContact.CollectionModel{
    private ShoppingItemDatabase shoppingItemDatabase;
    private List<Collections> collectionsList;
    @Override
    public List<Collections> getCollectionItem() {
        shoppingItemDatabase = ShoppingItemDatabase
                .getInstance(ApplicationContext.getContext());

        Future<List<Collections>> future = ThreadPool.threadPoolExecutor.submit(new Callable<List<Collections>>() {
            @Override
            public List<Collections> call() throws Exception {
                String username = shoppingItemDatabase.personnelInformationDao().getLoggingUsername();
                return shoppingItemDatabase.collectionsDao().getCollectionsList(username);
            }
        });

        try {
            collectionsList = future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return collectionsList;
    }
}
