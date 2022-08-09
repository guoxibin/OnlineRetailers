package com.example.onlineretailers.ui.home;

import com.example.onlineretailers.Bean.BaseBean;
import com.example.onlineretailers.Bean.Goods;
import com.example.onlineretailers.network_retrofit.HttpBinService;
import com.example.onlineretailers.network_retrofit.RetrofitClient;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class HomeModel implements HomeInterfaceContact.HomeModel {


    @Override
    public Flowable<BaseBean<List<Goods>>> getGoods() {
        return RetrofitClient
                .getInstance()
                .getService(HttpBinService.class)
                .getGoods();
    }
}
