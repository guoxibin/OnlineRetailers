package com.example.onlineretailers.ui.home;

import com.example.onlineretailers.Bean.BaseBean;
import com.example.onlineretailers.Bean.Goods;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;


public interface HomeInterfaceContact {

    interface HomePresenter {
        void getGoods();
    }

    interface HomeModel {
        Flowable<BaseBean<List<Goods>>> getGoods();
    }

    interface HomeView {
        void getGoodsSuccess(List<Goods> goods);
        void getGoodsFailure(Throwable throwable);
    }
}
