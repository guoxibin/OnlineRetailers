package com.example.onlineretailers.ui.login;

public interface LoginContract {

    interface LoginView {
        void getLoginResult(Boolean boo);
    }

    interface LoginPresenter{
        void getLoginReturnResult(String username, String password);
        void setDataBaseLoginState(String username);
    }

    interface LoginModel {
        boolean getLoginReturnResult(String username, String password);
        void setDataBaseLoginState(String username);
    }
}
