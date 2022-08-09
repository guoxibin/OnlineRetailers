package com.example.onlineretailers.ui.mine;

import com.example.onlineretailers.roomDatabase.PersonnelInformation;
import com.example.onlineretailers.roomDatabase.ShoppingItemDatabase;
import com.example.onlineretailers.roomDatabase.ThreadPool;
import com.example.onlineretailers.ui.context.ApplicationContext;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MineModel implements MineContract.MineModel {

    private ShoppingItemDatabase shoppingItemDatabase;
    private PersonnelInformation personnelInformation;//已登录人的信息
    @Override
    public PersonnelInformation getPersonnelInformation() {
        shoppingItemDatabase = ShoppingItemDatabase.getInstance(ApplicationContext.getContext());
        Future<PersonnelInformation> future = ThreadPool.threadPoolExecutor.submit((Callable<PersonnelInformation>) () ->
                shoppingItemDatabase.personnelInformationDao().getPersonnelInformation());

        try {
            personnelInformation =  future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return personnelInformation;
    }
}
