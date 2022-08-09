package com.example.onlineretailers.ui.purchasepage.payment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.onlineretailers.R;
import com.example.onlineretailers.ui.purchasepage.bean.PurchaseShopping;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class PaymentBottomSheetDialogFragment extends BottomSheetDialogFragment
        implements View.OnClickListener{

    private TextView totalPriceText; //总价
    private Button paymentBtn; //支付的按钮事件
    private String totalPrice; //总价
    private List<PurchaseShopping> purchaseShoppingList;//预购买列表
    private final PaymentBottomSheetDialogFragmentPresenter paymentBottomSheetDialogFragmentPresenter;

    public PaymentBottomSheetDialogFragment(String totalPrice, List<PurchaseShopping> purchaseShoppingList) {
        this.totalPrice = totalPrice;
        this.purchaseShoppingList = purchaseShoppingList;
        paymentBottomSheetDialogFragmentPresenter = new PaymentBottomSheetDialogFragmentPresenter();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog =  super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getContext(),
                R.layout.purchase_page_payment_bottom_sheet_dialog, null);
        dialog.setContentView(view);
        initView(view);//初始化view
        return dialog;

    }

    /**
     * 初始化view
     */
    private void initView(View view) {
        totalPriceText = view.findViewById(R.id.purchase_page_bottom_sheet_totalPrice);
        paymentBtn = view.findViewById(R.id.purchase_page_bottom_sheet_paymentBtn);
        totalPriceText.setText(totalPrice);
        paymentBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //判断余额是否足够支付
        if (paymentBottomSheetDialogFragmentPresenter.setEnoughPayment(totalPrice)) {
            paymentBottomSheetDialogFragmentPresenter.setDeletePrePurchase();//清除预购买列表
            paymentBottomSheetDialogFragmentPresenter
                    .setInsertHistoricalOrders(totalPrice, purchaseShoppingList);//加入历史订单
            onStop();//关闭此支付底页
            Toast.makeText(getContext(),"支付成功！", Toast.LENGTH_SHORT).show();
        } else {
            //余额不足
            Toast.makeText(getContext(), "剩余余额不足！", Toast.LENGTH_SHORT).show();
        }

    }
}
