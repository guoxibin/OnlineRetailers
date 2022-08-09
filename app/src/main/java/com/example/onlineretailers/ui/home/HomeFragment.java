package com.example.onlineretailers.ui.home;

import android.content.Intent;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.onlineretailers.Bean.Goods;
import com.example.onlineretailers.R;
import com.example.onlineretailers.ui.base.BaseFragment;
import com.example.onlineretailers.ui.context.ApplicationContext;
import com.example.onlineretailers.ui.details.GoodsDetailsActivity;
import com.example.onlineretailers.ui.home.adapter.HomeRecycleViewAdapter;
import com.example.onlineretailers.ui.home.adapter.HomeSpanSizeLookup;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment
        implements SwipeRefreshLayout.OnRefreshListener,
        HomeInterfaceContact.HomeView,
        HomeRecycleViewAdapter.ItemClickListener{


    private RecyclerView recyclerView;
    private HomeSpanSizeLookup homeSpanSizeLookup;
    private SwipeRefreshLayout swipeRefreshLayout;
    private GridLayoutManager gridLayoutManager;
    private HomeRecycleViewAdapter homeRecycleViewAdapter;
    private List<Goods> data = new ArrayList<>();
    private HomePresenter homePresenter;


    @Override
    protected int getLayoutInflate() {
        return R.layout.fragment_bottom_home;
    }


    @Override
    protected void initView() {
        swipeRefreshLayout = find(R.id.home_swipeRefresh);//初始化swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView = find(R.id.home_recyclerView);//初始化recyclerView
        gridLayoutManager =
                new GridLayoutManager(ApplicationContext.getContext(), 4);//设置一行分为四格
        homeSpanSizeLookup = new HomeSpanSizeLookup(data);
        gridLayoutManager.setSpanSizeLookup(homeSpanSizeLookup);//设置不同item占据的格子数
        recyclerView.setLayoutManager(gridLayoutManager);//设置布局给recyclerView

        homeRecycleViewAdapter = new HomeRecycleViewAdapter(data, recyclerView, getActivity());
        homeRecycleViewAdapter.setItemClickListener(this);
        recyclerView.setAdapter(homeRecycleViewAdapter);

        homePresenter = new HomePresenter(this);
        homePresenter.getGoods();
    }


    /**
     * 刷新监听
     */
    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        homePresenter.getGoods();
    }

    @Override
    public void getGoodsSuccess(List<Goods> goods) {
        homeSpanSizeLookup.setSpanSize(goods);
        homeRecycleViewAdapter.setGoods(goods);
    }

    @Override
    public void getGoodsFailure(Throwable throwable) {

    }

    @Override
    public void onItemOnClick(Goods goods) {
        Intent intent = new Intent(getActivity(), GoodsDetailsActivity.class);
        intent.putExtra(GoodsDetailsActivity.GOODS_ID, goods.getGoodsId());
        startActivity(intent);
    }
}
