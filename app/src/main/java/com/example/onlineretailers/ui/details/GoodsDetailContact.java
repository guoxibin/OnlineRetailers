package com.example.onlineretailers.ui.details;

import com.example.onlineretailers.Bean.BaseBean;
import com.example.onlineretailers.Bean.DetailData;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface GoodsDetailContact {

    interface IGoodsDetailPresenter {
        void getGoodsDetail(int goodsId);
        void setDetailCollection(DetailData detailData);
    }

    interface IGoodsDetailModel {
        Flowable<BaseBean<List<DetailData>>> getGoodsDetail();
        void setDetailCollection(DetailData detailData);

    }

    interface IGoodsDetailView {

        void getGoodsDetailSuccess(DetailData detailData);

        void getGoodsDetailError(Throwable throwable);
    }
}
