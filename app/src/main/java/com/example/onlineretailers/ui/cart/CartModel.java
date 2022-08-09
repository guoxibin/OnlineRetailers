package com.example.onlineretailers.ui.cart;

import android.os.Handler;

import com.example.onlineretailers.roomDatabase.Item;
import com.example.onlineretailers.roomDatabase.PersonnelInformation;
import com.example.onlineretailers.roomDatabase.PrePurchase;
import com.example.onlineretailers.roomDatabase.ShoppingItemDatabase;
import com.example.onlineretailers.roomDatabase.ThreadPool;
import com.example.onlineretailers.ui.cart.bean.ShoppingItem;
import com.example.onlineretailers.ui.context.ApplicationContext;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CartModel implements CartContact.CartDetailModel{
    private ShoppingItemDatabase shoppingItemDatabase;
    private List<Item> items;
    private String username;


    /**
     * 取得数据库数据
     * @return 商品列表
     */
    @Override
    public List<Item> getDetailDataForDatabase() {
        shoppingItemDatabase = ShoppingItemDatabase
                .getInstance(ApplicationContext.getContext());

        Future<List<Item>> future = ThreadPool.threadPoolExecutor.submit(() -> {
            //取得登录状态的用户id
            username = shoppingItemDatabase.personnelInformationDao().getLoggingUsername();
            items = shoppingItemDatabase.itemDao().getLoginItemList(username);
            return items;
        });

        try {
            items =  future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return items;
    }

    /**
     * 删除被选中子项布局的id
     * @param list 子项布局
     */
    @Override
    public void setDeleteShoppingItem(List<ShoppingItem> list, Handler handler) {
        if (shoppingItemDatabase == null) {
            shoppingItemDatabase = ShoppingItemDatabase.getInstance(ApplicationContext.getContext());
        }

        ThreadPool.threadPoolExecutor.execute(() -> {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).isCheck()) {
                    shoppingItemDatabase.itemDao().deleteByUid(list.get(i).getUid());//删除指定id的布局
                }
            }


            handler.sendEmptyMessage(2);
        });
    }


    /**
     * 点击立即购买后将数据存入预购买列表
     * @param shoppingItemList 被选中的列表
     */
    @Override
    public void setStoreIntoDatabase(List<ShoppingItem> shoppingItemList, Handler handler) {
        if (shoppingItemDatabase == null) {
            shoppingItemDatabase = ShoppingItemDatabase
                    .getInstance(ApplicationContext.getContext());
        }



        ThreadPool.threadPoolExecutor.execute(() -> {

            PersonnelInformation personnelInformation = shoppingItemDatabase
                    .personnelInformationDao().getPersonnelInformation();//用户信息


            for (int i = 0; i < shoppingItemList.size(); i++) {
                //checkbox被选中的就加入预购买数据库
                if (shoppingItemList.get(i).isCheck()) {
                    int uid = shoppingItemList.get(i).getUid();
                    Item item = shoppingItemDatabase.itemDao().getItemByUid(uid);//商品信息

                    //存储数据进预购买表
                    shoppingItemDatabase.prePurchaseDao().insertPrePurchase(new PrePurchase(
                            personnelInformation.username, item.getName(), item.getQuantity(),
                            personnelInformation.getPersonName(), personnelInformation.getPhoneNumber(),
                            item.getTotalPrice(), item.getImageUrl() ));
                }
            }


            //将线程内已存入数据库的消息通知给主线程
            handler.sendEmptyMessage(1);
        });

    }
}
