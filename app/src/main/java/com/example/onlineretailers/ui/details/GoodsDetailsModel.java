package com.example.onlineretailers.ui.details;

import android.util.Log;

import com.example.onlineretailers.Bean.BaseBean;
import com.example.onlineretailers.Bean.DetailData;
import com.example.onlineretailers.network_retrofit.HttpBinService;
import com.example.onlineretailers.network_retrofit.RetrofitClient;
import com.example.onlineretailers.roomDatabase.*;
import com.example.onlineretailers.ui.context.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import io.reactivex.rxjava3.core.Flowable;

public class GoodsDetailsModel implements GoodsDetailContact.IGoodsDetailModel{


    private ShoppingItemDatabase shoppingItemDatabase;
    /**
     * 网络请求
     * @return 装载数据的数据类
     */
    @Override
    public Flowable<BaseBean<List<DetailData>>> getGoodsDetail() {
        return RetrofitClient.getInstance()
                .getService(HttpBinService.class)
                .getGoodsDetails();
    }

    /**
     * 存储收藏数据进数据库
     * @param detailData 数据类
     */
    @Override
    public void setDetailCollection(DetailData detailData) {
        shoppingItemDatabase = ShoppingItemDatabase
                .getInstance(ApplicationContext.getContext());
        ThreadPool.threadPoolExecutor.execute(() -> {
            String username = shoppingItemDatabase.personnelInformationDao()
                    .getLoggingUsername();
            List<String> stringList = shoppingItemDatabase
                    .collectionsDao().getCollectionsShoppingName(username);

            Log.w("TAG", "setDetailCollection: " + stringList.size());

            //检验是否唯一
            if (stringList.size() != 0) {
                for (int i = 0; i < stringList.size(); i++) {
                    Log.w("TAG", "setDetailCollection: " + detailData.getName());
                    if (Objects.equals(detailData.getName(), stringList.get(i))) {
                        //相等说明收藏里不唯一，直接返回
                        return;
                    }
                }
            }


            shoppingItemDatabase.collectionsDao()
                    .insert(new Collections(username, detailData.getName(),
                            detailData.getPresent_price(), getCurrentPurchaseTime()));


        });
    }

    /**
     * 获取当前时间
     * @return 返回具体时间的string
     */
    private String getCurrentPurchaseTime() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }
}
