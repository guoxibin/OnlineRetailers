package com.example.onlineretailers.ui.mine.smallChange;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.example.onlineretailers.R;
import com.example.onlineretailers.ui.base.BaseActivity;

public class SmallChangeActivity extends BaseActivity{

    private EditText smallChangeEditText;
    private String smallChange;//零钱


    @Override
    protected int getLayoutInflate() {
        return R.layout.mine_small_change;
    }

    @Override
    protected void initView() {
        smallChangeEditText = findViewById(R.id.small_change);
        Button makeSureSmallChangeBtn = findViewById(R.id.small_change_make_sure);
        makeSureSmallChangeBtn.setOnClickListener(v -> {
            showDialog();//显示对话框
        });


    }

    /**
     * 展示对话框
     */
    private void showDialog() {
        AlertDialog.Builder alertBuilder =
                new AlertDialog.Builder(SmallChangeActivity.this);
        alertBuilder.setTitle("确认充值吗？");
        alertBuilder.setPositiveButton("确认充值", (dialog, which) -> {
            smallChange = smallChangeEditText.getText().toString();
            //返回数据给上个页面
            Intent intent = getIntent();
            intent.putExtra("data_return_1", smallChange);
            setResult(RESULT_OK, intent);
            finish();
        });

        alertBuilder.setNeutralButton("我再想想", (dialog, which) -> {
            dialog.cancel();
        });

        alertBuilder.show();//显示
    }


}
