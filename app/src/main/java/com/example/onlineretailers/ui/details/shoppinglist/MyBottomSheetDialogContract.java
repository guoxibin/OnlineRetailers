package com.example.onlineretailers.ui.details.shoppinglist;

import com.example.onlineretailers.Bean.*;

public interface MyBottomSheetDialogContract {
    interface BottomSheetDialogPresenter {
        void setDetailData(DetailData detailData, int count);
    }


    interface BottomSheetDialogModel {
        void setDetailData(DetailData detailData, int count);
    }
}
