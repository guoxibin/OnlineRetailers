package com.example.onlineretailers.ui.login;

public class LoginPresenter implements LoginContract.LoginPresenter{

    private LoginContract.LoginView loginView;
    private LoginContract.LoginModel loginModel;

    public LoginPresenter(LoginContract.LoginView loginView) {
        this.loginView = loginView;
        loginModel = new LoginModel();
    }

    @Override
    public void getLoginReturnResult(String username, String password) {
        Boolean boo = loginModel.getLoginReturnResult(username, password);
        loginView.getLoginResult(boo);
    }

    @Override
    public void setDataBaseLoginState(String username) {
        loginModel.setDataBaseLoginState(username);
    }
}
