package com.example.onlineretailers.ui.details.shoppinglist;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.onlineretailers.Bean.DetailData;
import com.example.onlineretailers.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.example.onlineretailers.ui.context.*;

public class MyBottomSheetDialogFragment extends BottomSheetDialogFragment
        implements View.OnClickListener {

    private ImageView bottomSheetImage;
    private TextView originalPriceText, presentPriceText, goodsCountText;
    private DetailData detailData;//商品信息
    private final MyBottomSheetDialogFragmentPresenter myBottomSheetDialogFragmentPresenter;

    public void setDetailData(DetailData detailData) {
        this.detailData = detailData;
    }

    public MyBottomSheetDialogFragment() {
        myBottomSheetDialogFragmentPresenter = new MyBottomSheetDialogFragmentPresenter();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.bottom_sheet_dialog, null);
        dialog.setContentView(view);
        initView(view);//底部菜单栏初始化
        return dialog;
    }

    /**
     * 初始化view
     * @param view 底部菜单栏的view
     */
    private void initView(View view) {
        bottomSheetImage = view.findViewById(R.id.bottom_sheet_goods_pic);//底部菜单栏显示的照片
        originalPriceText = view.findViewById(R.id.bottom_sheet_goods_original_price);//底部菜单栏显示原价的价格
        presentPriceText = view.findViewById(R.id.bottom_sheet_goods_present_price);//底部菜单栏显示现价的价格

        goodsCountText = view.findViewById(R.id.goods_count);
        Button addButton = view.findViewById(R.id.goods_add);
        Button minusButton = view.findViewById(R.id.goods_minus);

        //增加按钮，减少按钮，确认按钮
        Button confirmButton = view.findViewById(R.id.bottom_sheet_goods_confirm);

        addButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);
        confirmButton.setOnClickListener(this);

        setBottomSheetData();//设置购物底部菜单栏的值
    }

    /**
     * 设置bottomSheetDialog的值
     */
    private void setBottomSheetData() {
        Glide.with(bottomSheetImage)
                .load(detailData.getImageUrl())
                .into(bottomSheetImage);
        originalPriceText.setText(detailData.getOrigin_price());
        presentPriceText.setText(detailData.getPresent_price());
    }


    /**
     * 菜单栏的确认订单的点击事件
     * @param v button
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottom_sheet_goods_confirm:
                myBottomSheetDialogFragmentPresenter.setDetailData(detailData,
                        Integer.parseInt(goodsCountText.getText().toString()));

                //购买提醒
                Toast.makeText(ApplicationContext.getContext(), "已购买" + detailData.getName(),
                        Toast.LENGTH_SHORT).show();

                onStop();//关闭这个页面
                break;
            case R.id.goods_add:
                goodsCountText.setText(getAddInteger(goodsCountText.getText().toString()));
                break;
            case R.id.goods_minus:
                goodsCountText.setText(judgeGetMinusInteger(goodsCountText.getText().toString()));
                break;
        }
    }

    /**
     * 将数量string转化为Integer，并加1,再转化为String
     * @param str String类型的数量
     * @return  加1的int类型的数量
     */
    private String getAddInteger(String str) {
        int count =  Integer.parseInt(str) + 1;
        return Integer.toString(count);
    }

    /**
     * 将数量string转化为Integer，并-1,再转化为String
     * @param str String类型的数量
     * @return  减1的int类型的数量
     */
    private String judgeGetMinusInteger(String str) {
        int temp = Integer.parseInt(str);
        if (temp <= 1) {
            return Integer.toString(1);
        } else {
            return Integer.toString(temp - 1);
        }
    }


}
