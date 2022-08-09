package com.example.onlineretailers.ui.home;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenter implements HomeInterfaceContact.HomePresenter{
    private HomeInterfaceContact.HomeView homeView;
    private HomeInterfaceContact.HomeModel homeModel;

    public HomePresenter(HomeInterfaceContact.HomeView homeView) {
        this.homeView = homeView;
        homeModel = new HomeModel();
    }


    @Override
    public void getGoods() {
        homeModel.getGoods()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        listBaseBean -> homeView.getGoodsSuccess(listBaseBean.getData()),
                        throwable -> homeView.getGoodsFailure(throwable));


    }
}
