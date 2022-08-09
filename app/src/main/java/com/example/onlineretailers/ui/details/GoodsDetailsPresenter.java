package com.example.onlineretailers.ui.details;

import com.example.onlineretailers.Bean.DetailData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class GoodsDetailsPresenter implements GoodsDetailContact.IGoodsDetailPresenter {

    private final GoodsDetailContact.IGoodsDetailModel iGoodsDetailModel;
    private final GoodsDetailContact.IGoodsDetailView iGoodsDetailView;

    public GoodsDetailsPresenter(GoodsDetailContact.IGoodsDetailView iGoodsDetailView) {
        this.iGoodsDetailView = iGoodsDetailView;
        iGoodsDetailModel = new GoodsDetailsModel();
    }


    @Override
    public void getGoodsDetail(int goodsId) {
        iGoodsDetailModel.getGoodsDetail()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(goodsDetailBaseBean -> iGoodsDetailView
                                .getGoodsDetailSuccess(toConfirmGoodsLocation
                                        (goodsDetailBaseBean.getData(), goodsId)),
                        throwable -> iGoodsDetailView.getGoodsDetailError(throwable));
    }

    /**
     * 存储收藏数据
     * @param detailData 数据类
     */
    @Override
    public void setDetailCollection(DetailData detailData) {
        iGoodsDetailModel.setDetailCollection(detailData);
    }


    /**
     * 通过点击商品id确认商品位置，从而放回对应商品信息
     * @param detailDataList 商品列表
     * @param goodsId 点击的商品id
     * @return 对应商品信息
     */
    private DetailData toConfirmGoodsLocation(List<DetailData> detailDataList, int goodsId) {
        for (int i = 0; i < detailDataList.size(); i++) {
            if (goodsId == detailDataList.get(i).getGoodsId()) {
                return detailDataList.get(i);
            }
        }
        return null;
    }

}