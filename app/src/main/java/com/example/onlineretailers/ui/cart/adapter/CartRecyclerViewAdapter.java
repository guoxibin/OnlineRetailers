package com.example.onlineretailers.ui.cart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlineretailers.R;
import com.example.onlineretailers.ui.cart.bean.ShoppingItem;

import java.util.List;

public class CartRecyclerViewAdapter extends RecyclerView.Adapter<CartRecyclerViewAdapter.ShoppingItemView> {

    private final List<ShoppingItem> shoppingItemList;
    private final Context context;

    public CartRecyclerViewAdapter(List<ShoppingItem> itemList, Context context) {
        this.shoppingItemList = itemList;
        this.context = context;
    }

    public void setShoppingItemList(List<ShoppingItem> shoppingItemList) {
        this.shoppingItemList.clear();
        this.shoppingItemList.addAll(shoppingItemList);
        notifyDataSetChanged();
    }

   static class ShoppingItemView extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView textView;
        CheckBox checkBox;

        public ShoppingItemView(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_beautiful);
            textView = itemView.findViewById(R.id.text_beautiful);
            checkBox = itemView.findViewById(R.id.check_box);
            checkBox.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.check_box) {
                checkBox.setChecked(!checkBox.isChecked());
            }
        }
    }

    @NonNull
    @Override
    public ShoppingItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.cart_shopping_item, parent, false);
        return new ShoppingItemView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingItemView holder, int position) {
        ShoppingItem shoppingItem = shoppingItemList.get(position);
        Glide.with(holder.imageView)
                .load(shoppingItem.getgoodsOrderImage())
                .into(holder.imageView);
        holder.textView.setText(shoppingItem.getgoodsOrderText());

        holder.checkBox.setOnClickListener(null);
        holder.checkBox.setChecked(shoppingItem.isCheck());
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            shoppingItem.setCheck(isChecked);
        });

    }

    @Override
    public int getItemCount() {
        return shoppingItemList.size();
    }


}
