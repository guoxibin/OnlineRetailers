package com.example.onlineretailers.ui.register;

import com.example.onlineretailers.roomDatabase.PersonnelInformation;
import com.example.onlineretailers.roomDatabase.ShoppingItemDatabase;
import com.example.onlineretailers.roomDatabase.ThreadPool;
import com.example.onlineretailers.ui.context.ApplicationContext;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class RegisterModel implements RegisterContract.RegisterModel{

    private ShoppingItemDatabase shoppingItemDatabase;


    @Override
    public void insertDataBaseModel(String username, String personName, String password, String phoneNumber) {
        if (shoppingItemDatabase == null) {
            shoppingItemDatabase = ShoppingItemDatabase.getInstance(ApplicationContext.getContext());
        }
        ThreadPool.threadPoolExecutor.execute(() -> shoppingItemDatabase.personnelInformationDao()
                .insertPersonnelInformation(new PersonnelInformation(
                       username, personName, password, phoneNumber,0,"0")));
    }

    @Override
    public boolean getRegisterCheckResult(String username) {
        shoppingItemDatabase = ShoppingItemDatabase.getInstance(ApplicationContext.getContext());
        Future<List<String>> future = ThreadPool.threadPoolExecutor.submit(() ->
                shoppingItemDatabase.personnelInformationDao().getUsernameList());
        try {
            List<String> usernameList = future.get();

            for (int i = 0; i < usernameList.size(); i++) {
                if (Objects.equals(username, usernameList.get(i))) {
                    return false;//相同就返回false,因为要保证每个id唯一
                }
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return true;
    }

}
