package com.example.onlineretailers.ui.mine.collection.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineretailers.R;
import com.example.onlineretailers.ui.mine.collection.bean.ShoppingCollectionItem;

import java.util.List;

public class MineCollectionAdapter extends RecyclerView.Adapter<MineCollectionAdapter.ShoppingCollection>{

    private final Context context;
    private List<ShoppingCollectionItem> shoppingCollectionItemList;

    public MineCollectionAdapter(Context context, List<ShoppingCollectionItem> shoppingCollectionItemList) {
        this.context = context;
        this.shoppingCollectionItemList = shoppingCollectionItemList;
    }

    @NonNull
    @Override
    public ShoppingCollection onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mine_collections_item, parent, false);
        return new ShoppingCollection(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingCollection holder, int position) {
        ShoppingCollectionItem shoppingCollectionItem =shoppingCollectionItemList.get(position);
        holder.collectionShoppingName.setText(shoppingCollectionItem.getShoppingName());
        holder.collectionShoppingUnitPrice.setText(shoppingCollectionItem.getShoppingUnitPrice());
        holder.collectionTime.setText(shoppingCollectionItem.getCollectionTime());
    }

    @Override
    public int getItemCount() {
        return shoppingCollectionItemList.size();
    }

    static class ShoppingCollection extends RecyclerView.ViewHolder {

        public TextView collectionTime, collectionShoppingName, collectionShoppingUnitPrice;

        public ShoppingCollection(@NonNull View itemView) {
            super(itemView);

            collectionShoppingName = itemView.findViewById(R.id.collections_item_shopping_name);
            collectionTime = itemView.findViewById(R.id.collections_item_time);
            collectionShoppingUnitPrice = itemView.findViewById(R.id.collections_item_shopping_unitPrice);
        }
    }
 }
