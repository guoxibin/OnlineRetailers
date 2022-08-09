package com.example.onlineretailers.ui.mine;
import com.example.onlineretailers.roomDatabase.*;
public interface MineContract {

    interface MineModel {
        PersonnelInformation getPersonnelInformation();
    }

    interface MinePresenter {
        void getPersonnelInformation();
    }

    interface MineView {
        void getPersonnelInformation(PersonnelInformation personnelInformation);
    }
}
