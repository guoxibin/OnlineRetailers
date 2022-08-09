package com.example.onlineretailers.ui.mine.setting;

import android.content.Intent;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import com.example.onlineretailers.R;
import com.example.onlineretailers.ui.base.BaseActivity;
import com.example.onlineretailers.ui.login.LoginActivity;

public class SettingActivity extends BaseActivity{
    @Override
    protected int getLayoutInflate() {
        return R.layout.mine_setting;
    }

    @Override
    protected void initView() {

        Toolbar toolbar = findViewById(R.id.mine_setting_toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());//toolbar返回键触发finish()方法

        Button mineSettingChange = findViewById(R.id.mine_setting_changeAmountNumbers);
        mineSettingChange.setOnClickListener(v -> {

            AlertDialog.Builder alertBuilder =
                    new AlertDialog.Builder(SettingActivity.this);
            alertBuilder.setTitle("确认退出吗？");
            alertBuilder.setPositiveButton("确认", (dialog, which) -> {

                SettingPresenter settingPresenter = new SettingPresenter();
                settingPresenter.setLoginOffline();//促使此账号下线

                Intent intentChange = new Intent(this, LoginActivity.class);
                startActivity(intentChange);
            });

            alertBuilder.setNeutralButton("我再想想", (dialog, which) -> {
                dialog.cancel();
            });

            alertBuilder.show();//显示

        });



    }

}
