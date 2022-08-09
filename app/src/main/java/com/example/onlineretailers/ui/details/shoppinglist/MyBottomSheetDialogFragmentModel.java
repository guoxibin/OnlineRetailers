package com.example.onlineretailers.ui.details.shoppinglist;

import com.example.onlineretailers.Bean.DetailData;
import com.example.onlineretailers.roomDatabase.Item;
import com.example.onlineretailers.roomDatabase.ShoppingItemDatabase;
import com.example.onlineretailers.roomDatabase.ThreadPool;
import com.example.onlineretailers.ui.context.ApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyBottomSheetDialogFragmentModel implements MyBottomSheetDialogContract
        .BottomSheetDialogModel {

    private ShoppingItemDatabase shoppingItemDatabase;//数据库类


    /**
     * 将数据存入数据库的item表
     */
    @Override
    public void setDetailData(DetailData detailData, int count) {
        shoppingItemDatabase = ShoppingItemDatabase.getInstance(ApplicationContext.getContext());

        //线程池存取数据
                ThreadPool.threadPoolExecutor.execute(() ->
                        shoppingItemDatabase.itemDao()
                                .insertItem(new Item(shoppingItemDatabase.personnelInformationDao()
                                        .getLoggingUsername(), detailData.getName(),
                                        detailData.getPresent_price(), count,
                                        "￥" + getTotalPrice(detailData.getPresent_price(), count),
                                detailData.getImageUrl(), getCurrentPurchaseTime()))
                );
    }

    /**
     * 已知单价和数量
     * @param unitPrice 单价
     * @param count 数量
     * @return 总价
     */
    private String getTotalPrice(String unitPrice, int count) {
        String str = unitPrice.substring(1);
        int totalPrice = Integer.parseInt(str) * count;
        return Integer.toString(totalPrice);
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
