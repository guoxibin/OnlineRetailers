package com.example.onlineretailers.ui.purchasepage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.onlineretailers.R;
import com.example.onlineretailers.ui.purchasepage.bean.PurchaseShopping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PurchasePageShoppingAdapter extends RecyclerView.Adapter<PurchasePageShoppingAdapter
        .ShoppingItem> {
    private List<PurchaseShopping>  purchaseShoppingList;
    private Context context;


    public void setPurchaseShoppingList(List<PurchaseShopping> purchaseShoppingList) {
        this.purchaseShoppingList.clear();
        this.purchaseShoppingList.addAll(purchaseShoppingList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShoppingItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.purchase_page_business,
                parent, false);
        return new ShoppingItem(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingItem holder, int position) {

        PurchaseShopping purchaseShopping = purchaseShoppingList.get(position);
        holder.usernameText.setText(purchaseShopping.getUsername());
        holder.shoppingNameText.setText(purchaseShopping.getName());
        holder.purchaseCountText.setText(String.valueOf(purchaseShopping.getPurchaseCount()));
        holder.purchaseTimeText.setText(getCurrentPurchaseTime());
        holder.totalPrice.setText(purchaseShopping.getTotalPrice());

        Glide.with(holder.purchasePageImage)
                .load(purchaseShopping.getImageUrl())
                .into(holder.purchasePageImage);


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

    @Override
    public int getItemCount() {
        return purchaseShoppingList.size();
    }


    static class ShoppingItem extends RecyclerView.ViewHolder {

        TextView usernameText, shoppingNameText, purchaseTimeText,
        purchaseCountText, totalPrice;
        EditText purchasePageOrdersInformation;
        ImageView purchasePageImage;



        public ShoppingItem(@NonNull View itemView) {
            super(itemView);
            usernameText = itemView.findViewById(R.id.purchase_page_username);
            shoppingNameText = itemView.findViewById(R.id.purchase_page_purchaseShoppingName);
            purchaseTimeText = itemView.findViewById(R.id.purchase_page_purchaseTime);
            purchaseCountText = itemView.findViewById(R.id.purchase_page_purchaseCount);
            totalPrice = itemView.findViewById(R.id.purchase_page_purchaseAmount);
            purchasePageOrdersInformation = itemView.findViewById(
                    R.id.purchase_page_purchaseOrderInformation);
            purchasePageImage = itemView.findViewById(R.id.purchase_page_image);

        }
    }

    public PurchasePageShoppingAdapter(List<PurchaseShopping> purchaseShoppingList, Context context) {
        this.purchaseShoppingList = purchaseShoppingList;
        this.context = context;
    }
}
