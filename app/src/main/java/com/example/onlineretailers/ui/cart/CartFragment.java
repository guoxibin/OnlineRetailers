package com.example.onlineretailers.ui.cart;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.onlineretailers.R;
import com.example.onlineretailers.roomDatabase.Item;
import com.example.onlineretailers.ui.base.BaseFragment;
import com.example.onlineretailers.ui.cart.adapter.CartRecyclerViewAdapter;
import com.example.onlineretailers.ui.cart.bean.ShoppingItem;
import com.example.onlineretailers.ui.context.ApplicationContext;
import com.example.onlineretailers.ui.purchasepage.PurchasePageActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CartFragment extends BaseFragment implements View.OnClickListener,
        CartContact.CartDetailView, SwipeRefreshLayout.OnRefreshListener,
        PopupMenu.OnMenuItemClickListener{

    private CartRecyclerViewAdapter cartRecyclerViewAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CartPresenter cartPresenter;
    private int clickCount = 0; //点击次数，次数为单表示全选，次数为双表示全不选

    private final List<ShoppingItem> shoppingItemList = new ArrayList<>();
    private final Handler handler = new Handler(Looper.getMainLooper(), msg -> {
        if (msg.what == 1) {
            setAdapterListChange();//通知购物车列表数据已改变
        }
        if (msg.what == 2) {
            //list的remove()是个巨坑，它会自动跳过某个下标，解决方法是逆序遍历
            for (int i = shoppingItemList.size() - 1; i >= 0; i--) {
                if (shoppingItemList.get(i).isCheck()) {
                    shoppingItemList.remove(i);
                }
            }

            cartRecyclerViewAdapter.notifyDataSetChanged();
        }
        return true;
    });

    @Override
    protected int getLayoutInflate() {
        return R.layout.fragment_bottom_cart;
    }

    @Override
    protected void initView() {
        TextView cartSettingView = find(R.id.cart_setting);
        cartSettingView.setOnClickListener(this);

        swipeRefreshLayout = find(R.id.cart_swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        RecyclerView cart_recyclerView = find(R.id.cart_recyclerView);
        cart_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        cartRecyclerViewAdapter = new CartRecyclerViewAdapter(shoppingItemList, getActivity());
        cart_recyclerView.setAdapter(cartRecyclerViewAdapter);


        cartPresenter = new CartPresenter(this);
        cartPresenter.getDetailData();

    }


    /**
     * 菜单栏点击
     * @param v 菜单栏项
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cart_setting) {
            PopupMenu popupMenu = new PopupMenu(ApplicationContext.getContext(), v);

            //把创建的菜单xml传进去
            popupMenu.getMenuInflater().inflate(R.menu.cart_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();
        }
    }



    /**
     * 从数据库返回购物车购买项目的信息
     * @param list 购物车信息列表
     */
    @Override
    public void getSuccessOrdersInformation(List<Item> list) {
        if (list.size() == 0) {
            Toast.makeText(getContext(), "购物车列表内没有商品", Toast.LENGTH_SHORT).show();
            return;
        }
        shoppingItemList.clear();
        Collections.reverse(list);//将链表反转
        for (int i = 0; i < list.size(); i++) {
            shoppingItemList.add(new ShoppingItem(list.get(i).uid, list.get(i).getImageUrl(),
                    getCartText(list.get(i)), false ));
        }
    }

    /**
     * 对数据库拿取的String信息进行组装
     * @param item 数据库拿取的商品的信息
     * @return 组装好的string信息
     */
    private String getCartText(Item item) {
        //判断是否包含￥
        if (item.getTotalPrice().contains("￥")) {
            return item.getName() + "\n"
                    + item.getUnitPrice() + "x" + item.getQuantity() + "\n"
                    + "总价:" + item.getTotalPrice();
        } else return item.getName() + "\n"
                + item.getUnitPrice() + "x" + item.getQuantity() + "\n"
                + "总价: ￥" + item.getTotalPrice();

    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cart_buying:
                Intent intentCartBuying = new Intent(getActivity(), PurchasePageActivity.class);
                startActivity(intentCartBuying);
                storeIntoDatabase();//将购买的商品加入预购买列表
                break;
            case R.id.cart_delete:
                setAdapterListChange();//删除被选中信息
                break;
            case R.id.cart_select_all: //全选按钮
                clickCount++;
                allItemChecked(clickCount % 2 != 0);
                cartRecyclerViewAdapter.notifyDataSetChanged();
                break;
        }
        return false;
    }

    /**
     * 是否全选
     * @param isCheck 全选状态
     */
    private void allItemChecked(boolean isCheck) {
        for (int i = 0; i < shoppingItemList.size(); i++) {
            shoppingItemList.get(i).setCheck(isCheck);
        }
    }

    /**
     * 通知CartRecyclerViewAdapter数据发生改变
     */
    private void setAdapterListChange() {
        /*//要是无点击事件则返回
        if (onClickList.size() == 0) {
            return;
        }

        //将点击到的item的下标值对应到shoppingItemList,并移除
        for (int i = 0; i < onClickList.size(); i++) {
            shoppingItemList.remove(onClickList.get(i));
            cartPresenter.setDeleteShoppingItem(onClickList.get(i).getUid());//删除数据库中被选中的item
        }
        onClickList.clear();
        onRefresh();//刷新视图*/

        if (shoppingItemList.size() == 0) {
            return;
        }
        //删除购物车列表被选中的子项
        cartPresenter.setDeleteShoppingItem(shoppingItemList, handler);
    }

    /**
     * 下拉刷新监听
     */
    @Override
    public void onRefresh() {
        cartPresenter.getDetailData();
        cartRecyclerViewAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    /**
     *将购买信息存储进预购买列表
     */
    private void storeIntoDatabase() {
        cartPresenter.setStoreIntoDatabase(shoppingItemList, handler);
    }


}
