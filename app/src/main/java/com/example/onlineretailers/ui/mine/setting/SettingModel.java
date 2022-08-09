package com.example.onlineretailers.ui.mine.setting;
import com.example.onlineretailers.roomDatabase.*;
import com.example.onlineretailers.ui.context.*;
public class SettingModel implements SettingContact.SettingModel{


    private ShoppingItemDatabase shoppingItemDatabase;
    @Override
    public void setLoginOffline() {
        shoppingItemDatabase = ShoppingItemDatabase
                .getInstance(ApplicationContext.getContext());

        ThreadPool.threadPoolExecutor.execute(() -> {
            String username = shoppingItemDatabase.personnelInformationDao().getLoggingUsername();

            //将账号的登录状态转为0
            shoppingItemDatabase.personnelInformationDao().setLoginOffline(username);
        });
    }
}
