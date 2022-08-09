package com.example.onlineretailers.ui.login;

import com.example.onlineretailers.roomDatabase.PersonnelInformation;
import com.example.onlineretailers.roomDatabase.ShoppingItemDatabase;
import com.example.onlineretailers.roomDatabase.ThreadPool;
import com.example.onlineretailers.ui.context.ApplicationContext;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class LoginModel implements LoginContract.LoginModel{

    private List<PersonnelInformation> personnelInformationList;
    private ShoppingItemDatabase shoppingItemDatabase;


    @Override
    public boolean getLoginReturnResult(String username, String password) {
        return comparisonDatabase(username, password);
    }

    @Override
    public void setDataBaseLoginState(String username) {
        ThreadPool.threadPoolExecutor.execute(() -> {
            shoppingItemDatabase.personnelInformationDao().updateLoginState(username);
        });
    }

    /**
     * 输入的用户名和密码跟数据库对比
     */
    private boolean comparisonDatabase(String username, String password) {
        if (username == null && password == null) {
            return false;
        }
        shoppingItemDatabase = ShoppingItemDatabase.getInstance(ApplicationContext.getContext());

        //进行线程池的同步操作
        Future<List<PersonnelInformation>> future = ThreadPool.threadPoolExecutor.submit(() -> {
            personnelInformationList = shoppingItemDatabase
                    .personnelInformationDao().getPersonnelInformationList();
            return personnelInformationList;
        });
        try {
            personnelInformationList = future.get();

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        for(PersonnelInformation personnelInformation : personnelInformationList) {
            if (personnelInformation.getUsername().equals(username)
                    && personnelInformation.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

}
