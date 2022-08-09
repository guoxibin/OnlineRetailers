package com.example.onlineretailers.ui.details.shoppinglist;

import com.example.onlineretailers.Bean.DetailData;

public class MyBottomSheetDialogFragmentPresenter implements MyBottomSheetDialogContract
        .BottomSheetDialogPresenter{

    private final MyBottomSheetDialogFragmentModel myBottomSheetDialogFragmentModel;


    public MyBottomSheetDialogFragmentPresenter() {
        this.myBottomSheetDialogFragmentModel = new MyBottomSheetDialogFragmentModel();
    }

    @Override
    public void setDetailData(DetailData detailData, int count) {
        myBottomSheetDialogFragmentModel.setDetailData(detailData, count);
    }
}
