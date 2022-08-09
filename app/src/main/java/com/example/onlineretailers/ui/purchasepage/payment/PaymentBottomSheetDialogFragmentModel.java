package com.example.onlineretailers.ui.purchasepage.payment;

import com.example.onlineretailers.roomDatabase.HistoricalOrders;
import com.example.onlineretailers.roomDatabase.ShoppingItemDatabase;
import com.example.onlineretailers.roomDatabase.ThreadPool;
import com.example.onlineretailers.ui.context.ApplicationContext;
import com.example.onlineretailers.ui.purchasepage.bean.PurchaseShopping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class PaymentBottomSheetDialogFragmentModel implements PaymentBottomSheetDialogContact
        .PaymentBottomSheetDialogFragmentModel{

    private ShoppingItemDatabase shoppingItemDatabase;


    /**
     * 购买后清除预购买列表的订单
     */
    @Override
    public void setDeletePrePurchase() {
        if (shoppingItemDatabase == null) {
            shoppingItemDatabase = ShoppingItemDatabase.getInstance(ApplicationContext.getContext());
        }

        ThreadPool.threadPoolExecutor.execute(() -> {
            shoppingItemDatabase.prePurchaseDao().deleteAll();
        });
    }


    /**
     * 得到是否足够金额付款
     * @param totalPrice 购买的商品的总价
     * @return 是否足够的结果
     */
    @Override
    public boolean setEnoughPayment(String totalPrice) {
        shoppingItemDatabase = ShoppingItemDatabase.getInstance(ApplicationContext.getContext());

        Future<Boolean> future = ThreadPool.threadPoolExecutor.submit(() -> {
            String str = shoppingItemDatabase.personnelInformationDao().getLoginSmallChange();
            //如果包含￥
            if (totalPrice.contains("￥")) {
                String substring = totalPrice.substring(1);
                //如果我的零钱大于要支付的钱
                if(Integer.parseInt(substring) <= Integer.parseInt(str)) {
                    //支付后零钱设置新值
                    shoppingItemDatabase.personnelInformationDao().setSmallChangeAfterPayment(
                                    String.valueOf(Integer.parseInt(str) - Integer.parseInt(substring))
                            );

                    return true;
                } else {
                    return false;
                }
                //如果不包含￥
            } else if (Integer.parseInt(totalPrice) <= Integer.parseInt(str)) {
                shoppingItemDatabase.personnelInformationDao().setSmallChangeAfterPayment(
                        String.valueOf(Integer.parseInt(str) - Integer.parseInt(totalPrice)));
                return true;
            } else {
                return false;
            }
        });


        Boolean boo = false;
        try {
            boo = future.get();//比较结果
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return boo;//返回比较结果
    }

    /**
     * 加入历史订单
     * @param totalPrice 总价
     * @param purchaseShoppingList 已购买列表
     */
    @Override
    public void setInsertHistoricalOrders(String totalPrice, List<PurchaseShopping> purchaseShoppingList) {
        if (purchaseShoppingList.size() == 0) {
            //要是列表内没有数据，即为0，直接返回
            return;
        }
        shoppingItemDatabase = ShoppingItemDatabase.getInstance(ApplicationContext.getContext());
        ThreadPool.threadPoolExecutor.execute(() -> {
            String username = shoppingItemDatabase.personnelInformationDao().getLoggingUsername();


            for (int i = 0; i < purchaseShoppingList.size(); i++) {
                shoppingItemDatabase.historicalOrdersDao()
                        .insert(new HistoricalOrders(username,
                                purchaseShoppingList.get(i).getName(),
                                purchaseShoppingList.get(i).getPurchaseCount(),
                                totalPrice, getCurrentPurchaseTime()));
            }
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
