package com.example.onlineretailers.network_retrofit;

import com.example.onlineretailers.Bean.BaseBean;
import com.example.onlineretailers.Bean.DetailData;
import com.example.onlineretailers.Bean.Goods;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;

public interface HttpBinService {



    @GET("lumaifans")
    Flowable<BaseBean<List<Goods>>> getGoods();

    @GET("goods_details_information")
    Flowable<BaseBean<List<DetailData>>> getGoodsDetails();


}
