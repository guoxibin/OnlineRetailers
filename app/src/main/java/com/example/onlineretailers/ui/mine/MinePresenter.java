package com.example.onlineretailers.ui.mine;

import android.util.Log;

import com.example.onlineretailers.roomDatabase.*;

public class MinePresenter implements MineContract.MinePresenter{
    private MineContract.MineModel mineModel;
    private MineContract.MineView mineView;

    public MinePresenter(MineContract.MineView mineView) {
        this.mineView = mineView;
        mineModel = new MineModel();
    }

    @Override
    public void getPersonnelInformation() {
        PersonnelInformation personnelInformation = mineModel.getPersonnelInformation();
        mineView.getPersonnelInformation(personnelInformation);
    }
}
