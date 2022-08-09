package com.example.onlineretailers.ui.mine.historicalOrders;

import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.onlineretailers.R;
import com.example.onlineretailers.roomDatabase.HistoricalOrders;
import com.example.onlineretailers.ui.base.BaseActivity;
import com.example.onlineretailers.ui.mine.historicalOrders.adapter.HistoricalOrdersAdapter;
import com.example.onlineretailers.ui.mine.historicalOrders.bean.HistoricalOrdersItem;

import java.util.ArrayList;
import java.util.List;


public class HistoricalOrdersActivity extends BaseActivity
        implements SwipeRefreshLayout.OnRefreshListener, HistoricalOrderContact.HistoricalOrderView{

    private SwipeRefreshLayout swipeRefreshLayout;
    private HistoricalOrderPresenter historicalOrderPresenter;
    private HistoricalOrdersAdapter historicalOrdersAdapter;
    private final List<HistoricalOrdersItem> historicalOrdersItemsList = new ArrayList<>();


    @Override
    protected int getLayoutInflate() {
        return R.layout.mine_historical_orders;
    }

    @Override
    protected void initView() {
        Toolbar toolbar = findViewById(R.id.mine_historical_toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());//toolbar返回键触发finish()方法

        swipeRefreshLayout = findViewById(R.id.mine_historical_swipe);
        swipeRefreshLayout.setOnRefreshListener(this);

        historicalOrderPresenter = new HistoricalOrderPresenter(this);
        historicalOrderPresenter.getSuccessHistoricalData();

        RecyclerView recyclerView = findViewById(R.id.mine_historical_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        historicalOrdersAdapter = new HistoricalOrdersAdapter(this, historicalOrdersItemsList);
        recyclerView.setAdapter(historicalOrdersAdapter);

    }

    @Override
    public void onRefresh() {
        historicalOrderPresenter.getSuccessHistoricalData();
        historicalOrdersAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    /**
     * 从历史记录中取到数据
     * @param historicalOrdersList 历史记录列表
     */
    @Override
    public void getSuccessHistoricalData(List<HistoricalOrders> historicalOrdersList) {
        if (historicalOrdersList.size() == 0) {
            Toast.makeText(this, "没有历史订单!", Toast.LENGTH_SHORT).show();
            return;
        }

        historicalOrdersItemsList.clear();//清除之前的数据
        for (int i = 0; i < historicalOrdersList.size(); i++) {
            historicalOrdersItemsList.add(new HistoricalOrdersItem(
                    historicalOrdersList.get(i).getName(), historicalOrdersList.get(i).getQuantity(),
                    historicalOrdersList.get(i).getPurchaseTime(), historicalOrdersList.get(i).getTotalPrice()
            ));
        }

    }
}
