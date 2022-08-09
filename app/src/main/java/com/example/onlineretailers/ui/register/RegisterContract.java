package com.example.onlineretailers.ui.register;

public interface RegisterContract {
    interface RegisterPresenter {
        void insertDataBasePresenter(String username, String personName,
                            String password, String phoneNumber);
        void getRegisterCheckResult(String username);

    }

    interface RegisterModel {
        void insertDataBaseModel(String username, String personName,
                                 String password, String phoneNumber);
        boolean getRegisterCheckResult(String username);
    }

    interface RegisterView {
        void getRegisterCheckResult(boolean boo);
    }


}
