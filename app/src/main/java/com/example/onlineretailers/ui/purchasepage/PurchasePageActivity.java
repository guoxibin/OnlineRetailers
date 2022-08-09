package com.example.onlineretailers.ui.purchasepage;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.onlineretailers.R;
import com.example.onlineretailers.roomDatabase.PrePurchase;
import com.example.onlineretailers.ui.base.BaseActivity;
import com.example.onlineretailers.ui.purchasepage.adapter.PurchasePageShoppingAdapter;
import com.example.onlineretailers.ui.purchasepage.bean.PurchaseShopping;
import com.example.onlineretailers.ui.purchasepage.payment.PaymentBottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class PurchasePageActivity extends BaseActivity implements PurchasePageContact.PurchasePageView,
        View.OnClickListener, SwipeRefreshLayout.OnRefreshListener{

    private TextView purchasePagePersonName, purchasePagePhoneNumbers,purchasePageLocation;
    private PurchasePagePresenter purchasePagePresenter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private PurchasePageShoppingAdapter purchasePageShoppingAdapter;
    private final List<PurchaseShopping> purchaseShoppingList = new ArrayList<>();
    @Override
    protected int getLayoutInflate() {
        return R.layout.activity_purchase_page;
    }

    @Override
    protected void initView() {
        purchasePagePersonName = findViewById(R.id.purchase_page_person_name);
        purchasePagePhoneNumbers = findViewById(R.id.purchase_page_phone_numbers);
        purchasePageLocation = findViewById(R.id.purchase_page_location);

        Button purchasePageBtn = findViewById(R.id.purchase_page_purchaseBtn);
        purchasePageBtn.setOnClickListener(this);

        purchasePagePresenter = new PurchasePagePresenter(this);
        purchasePagePresenter.getPurchaseInformation();//获得预购买列表中的数据

        swipeRefreshLayout = findViewById(R.id.purchase_page_swipe);
        swipeRefreshLayout.setOnRefreshListener(this);

        RecyclerView purchasePageRecyclerView = findViewById(R.id.purchase_shopping_recyclerview);
        purchasePageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        purchasePageShoppingAdapter = new PurchasePageShoppingAdapter(
                purchaseShoppingList, this);
        purchasePageRecyclerView.setAdapter(purchasePageShoppingAdapter);


    }

    /**
     * 拿到预购买列表中的数据
     * @param prePurchasesList 预购买列表
     */
    @Override
    public void getPurchaseInformation(List<PrePurchase> prePurchasesList) {
        if (prePurchasesList.size() == 0) {
            purchaseShoppingList.clear();
            Toast.makeText(this, "预购买商品列表无商品", Toast.LENGTH_SHORT).show();
        } else {
            purchasePagePersonName.setText(prePurchasesList.get(0).getPersonName());
            purchasePagePhoneNumbers.setText(prePurchasesList.get(0).getPhoneNumber());
            String username = prePurchasesList.get(0).getUsername();

            purchaseShoppingList.clear();//清除之前的数据

            for (int i = 0; i < prePurchasesList.size(); i++) {
                purchaseShoppingList.add(new PurchaseShopping(username,
                        prePurchasesList.get(i).getName(), prePurchasesList.get(i).getQuantity(),
                        prePurchasesList.get(i).getTotalPrice(), prePurchasesList.get(i).getImageUrl()));
            }
        }
    }

    /**
     * ”确定支付“点击
     * @param view 点击事件
     */
    @Override
    public void onClick(View view) {
        //支付页面
        if (view.getId() == R.id.purchase_page_purchaseBtn) {
            String str = getTotalPrice();//获得总价
            PaymentBottomSheetDialogFragment paymentBottomSheetDialogFragment =
                    new PaymentBottomSheetDialogFragment(str, purchaseShoppingList);
            paymentBottomSheetDialogFragment.show(getSupportFragmentManager()
                    ,"PaymentBottomSheetDialogFragment");
        }
    }

    /**
     * 点击支付事件后，合并获得所有商品价格之和，即总价
     */
    private String getTotalPrice() {
        int sumPrice = 0;
        for (int i = 0 ; i < purchaseShoppingList.size(); i++) {
            sumPrice = sumPrice + getTotalPriceInt(purchaseShoppingList.get(i).getTotalPrice());
        }

        return "￥" + sumPrice;
    }

    /**
     * 由于数据库存储的为string,转化为int
     * @param totalPrice string类型的总价
     * @return int类型的总价
     */
    private int getTotalPriceInt(String totalPrice) {
        if (totalPrice.contains("￥")) {
            String total = totalPrice.substring(1);
            return Integer.parseInt(total);
        }
        return Integer.parseInt(totalPrice);
    }

    /**
     * 刷新监听
     */
    @Override
    public void onRefresh() {
        purchasePagePresenter.getPurchaseInformation();
        purchasePageShoppingAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

}
