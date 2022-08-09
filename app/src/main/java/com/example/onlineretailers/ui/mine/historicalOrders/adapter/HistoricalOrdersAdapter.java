package com.example.onlineretailers.ui.mine.historicalOrders.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineretailers.R;
import com.example.onlineretailers.ui.mine.historicalOrders.bean.HistoricalOrdersItem;

import java.util.List;

public class HistoricalOrdersAdapter extends RecyclerView
        .Adapter<HistoricalOrdersAdapter.HistoricalOrders> {

    private final Context context;
    private final List<HistoricalOrdersItem> historicalOrdersItemsList;

    public HistoricalOrdersAdapter(Context context, List<HistoricalOrdersItem> historicalOrdersItemsList) {
        this.context = context;
        this.historicalOrdersItemsList = historicalOrdersItemsList;
    }


    @NonNull
    @Override
    public HistoricalOrders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.mine_historical_orders_item, parent,false);
        return new HistoricalOrders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoricalOrders holder, int position) {
        HistoricalOrdersItem historicalOrdersItem = historicalOrdersItemsList.get(position);

        holder.shoppingName.setText(historicalOrdersItem.getShoppingName());
        holder.shoppingCount.setText(String.valueOf(historicalOrdersItem.getShoppingCount()));
        holder.purchaseTimeText.setText(historicalOrdersItem.getPurchaseTime());
        holder.amountText.setText(historicalOrdersItem.getTotalPrice());
    }

    @Override
    public int getItemCount() {
        return historicalOrdersItemsList.size();
    }

    static class HistoricalOrders extends RecyclerView.ViewHolder {

        TextView purchaseTimeText, amountText, shoppingName, shoppingCount;

        public HistoricalOrders(@NonNull View itemView) {
            super(itemView);
            shoppingName = itemView.findViewById(R.id.mine_historical_shoppingName);
            shoppingCount = itemView.findViewById(R.id.mine_historical_shoppingCount);
            purchaseTimeText = itemView.findViewById(R.id.mine_historical_purchaseTime);
            amountText = itemView.findViewById(R.id.mine_historical_amount);
        }
    }
}
