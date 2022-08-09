package com.example.onlineretailers.ui.mine.setting;

public class SettingPresenter implements SettingContact.SettingPresenter{

    private SettingContact.SettingModel settingModel;

    public SettingPresenter() {
        settingModel = new SettingModel();
    }

    @Override
    public void setLoginOffline() {
        settingModel.setLoginOffline();
    }
}
