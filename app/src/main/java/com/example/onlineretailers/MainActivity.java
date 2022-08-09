package com.example.onlineretailers;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.onlineretailers.ui.base.ActivityCollector;
import com.example.onlineretailers.ui.base.BaseActivity;
import com.example.onlineretailers.ui.cart.CartFragment;
import com.example.onlineretailers.ui.context.ApplicationContext;
import com.example.onlineretailers.ui.home.HomeFragment;
import com.example.onlineretailers.ui.mine.MineFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity {

    private Fragment[] fragments;
    private int lastFragmentIndex = 0;
    private static final String[]  PERMISSION = {
            "android.permission.WRITE_EXTERNAL_STORAGE"
            ,"android.permission.READ_EXTERNAL_STORAGE"};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        applyPermission();//申请权限
    }

    /**
     * 权限申请
     */
    private void applyPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        || ContextCompat.checkSelfPermission(ApplicationContext.getContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, PERMISSION, 1);
        } else {
            return;
        }
    }

    /**
     * 权限申请
     * @param requestCode 请求码
     * @param permissions 权限集合
     * @param grantResults 权限处理结果集合
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1 :
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    return;
                }
        }
    }

    @Override
    protected int getLayoutInflate() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        BottomNavigationView bottomNavigationView
                = findViewById(R.id.bottom_navigation);

        fragments = new Fragment[] {
                new HomeFragment(),
                new CartFragment(),
                new MineFragment()
        };

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_frame,fragments[0])
                .commit();


        //底部导航栏子项点击监听
        bottomNavigationView.setOnItemSelectedListener(item -> {
            item.setChecked(true);
            switch(item.getItemId()) {
                case R.id.bottom_home:
                    switchFragment(0);
                    break;
                case R.id.bottom_cart:
                    switchFragment(1);
                    break;
                case R.id.bottom_mine:
                    switchFragment(2);
                    break;
            }
            return false;
        });


    }


    /**
     * fragment跟随底部navigation的子项点击而跳转，从lastFragmentIndex到to
     * @param to 跳转的位置
     */
    private void switchFragment(int to) {
        if (lastFragmentIndex == to) {
            return;
        }

        FragmentTransaction fragmentTransaction
                = getSupportFragmentManager().beginTransaction();//创建一个fragment事务实例

        //判断想要跳转到的页面是否被添加过
        if (!fragments[to].isAdded()) {
            fragmentTransaction.add(R.id.main_frame, fragments[to]);
        } else {
            fragmentTransaction.show(fragments[to]);
        }
        //隐藏之前跳转前的页面
        fragmentTransaction.hide(fragments[lastFragmentIndex])
                .commit();//

        lastFragmentIndex = to;//将跳转后的页面位置赋值给lastFragmentIndex
    }


    @Override
    public void onBackPressed() {
        showBackDialog();//退出程序
    }

    /**
     * 退出程序的dialog页面展示
     */
    private void showBackDialog() {
        AlertDialog.Builder alertBuilder =
                new AlertDialog.Builder(MainActivity.this);
        alertBuilder.setTitle("确认退出此程序吗？");
        alertBuilder.setPositiveButton("确认", (dialog, which) -> {
            ActivityCollector.finishAll();//退出程序并中止代码
        });

        alertBuilder.setNeutralButton("我再想想", (dialog, which) -> {
            dialog.cancel();
        });

        alertBuilder.show();//显示
    }
}
