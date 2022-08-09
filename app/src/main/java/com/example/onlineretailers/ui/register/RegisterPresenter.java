package com.example.onlineretailers.ui.register;

public class RegisterPresenter implements RegisterContract.RegisterPresenter{

    private final RegisterContract.RegisterModel registerModel;
    private final RegisterContract.RegisterView registerView;

    public RegisterPresenter(RegisterContract.RegisterView registerView) {
        this.registerView = registerView;
        registerModel = new RegisterModel();
    }

    @Override
    public void insertDataBasePresenter(String username, String personName, String password, String phoneNumber) {
        registerModel.insertDataBaseModel(username, personName, password, phoneNumber);
    }

    @Override
    public void getRegisterCheckResult(String username) {
        boolean boo = registerModel.getRegisterCheckResult(username);
        registerView.getRegisterCheckResult(boo);
    }


}
