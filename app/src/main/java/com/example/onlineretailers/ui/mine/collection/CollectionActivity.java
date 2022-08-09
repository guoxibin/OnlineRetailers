package com.example.onlineretailers.ui.mine.collection;

import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.onlineretailers.R;
import com.example.onlineretailers.roomDatabase.Collections;
import com.example.onlineretailers.ui.base.BaseActivity;
import com.example.onlineretailers.ui.mine.collection.adapter.MineCollectionAdapter;
import com.example.onlineretailers.ui.mine.collection.bean.ShoppingCollectionItem;

import java.util.ArrayList;
import java.util.List;

public class CollectionActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,
CollectionContact.CollectionView{

    private MineCollectionAdapter mineCollectionAdapter;
    private CollectionPresenter collectionPresenter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<ShoppingCollectionItem> shoppingCollectionItemList = new ArrayList<>();

    @Override
    protected int getLayoutInflate() {
        return R.layout.mine_collections;
    }

    @Override
    protected void initView() {

        Toolbar toolbar = findViewById(R.id.mine_collection_toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());//toolbar返回键触发finish()方法

        swipeRefreshLayout = findViewById(R.id.mine_collection_swipe);
        swipeRefreshLayout.setOnRefreshListener(this);

        collectionPresenter = new CollectionPresenter(this);
        collectionPresenter.getCollectionItem();

        RecyclerView recyclerView = findViewById(R.id.mine_collections_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mineCollectionAdapter = new MineCollectionAdapter(this, shoppingCollectionItemList);
        recyclerView.setAdapter(mineCollectionAdapter);



    }

    //刷新监听
    @Override
    public void onRefresh() {
        collectionPresenter.getCollectionItem();
        mineCollectionAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }


    /**
     * 从收藏数据库拿到数据
     * @param collectionsList 返回的已收藏列表
     */
    @Override
    public void getCollectionItem(List<Collections> collectionsList) {
        if (collectionsList.size() == 0) {
            Toast.makeText(this, "没有收藏的商品", Toast.LENGTH_SHORT).show();
            return;
        }

        shoppingCollectionItemList.clear();
        for (int i = 0; i < collectionsList.size(); i++) {
            shoppingCollectionItemList.add(new ShoppingCollectionItem(
                    collectionsList.get(i).getName(),
                    collectionsList.get(i).getUnitPrice(),
                    collectionsList.get(i).getCollectionTime()
            ));
        }
    }
}
