package com.example.onlineretailers.ui.mine;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.example.onlineretailers.R;
import com.example.onlineretailers.roomDatabase.PersonnelInformation;
import com.example.onlineretailers.roomDatabase.ShoppingItemDatabase;
import com.example.onlineretailers.roomDatabase.ThreadPool;
import com.example.onlineretailers.ui.base.BaseFragment;
import com.example.onlineretailers.ui.mine.collection.CollectionActivity;
import com.example.onlineretailers.ui.mine.historicalOrders.HistoricalOrdersActivity;
import com.example.onlineretailers.ui.mine.setting.SettingActivity;
import com.example.onlineretailers.ui.mine.smallChange.SmallChangeActivity;


public class MineFragment extends BaseFragment implements MineContract.MineView
                                                        , View.OnClickListener {

    private TextView loginIdText,smallChangeText, collectionText,
            historicalOrdersText, setUpText;
    private MinePresenter minePresenter;
    private ActivityResultLauncher activityResultLauncher;
    private Button smallChangeRefreshBtn;
    private String smallChange;//零花钱
    private ShoppingItemDatabase shoppingItemDatabase;


    @Override
    protected int getLayoutInflate() {
        return R.layout.fragment_bottom_mine;
    }


    /**
     * 把总金额存入数据库
     */
    private void setSmallChangeDatabase(Context context, String amount) {
        ThreadPool.threadPoolExecutor.execute(() -> {
            shoppingItemDatabase = ShoppingItemDatabase.getInstance(context);
            shoppingItemDatabase.personnelInformationDao().setSmallChange(amount);
        });
    }



    @Override
    protected void initView() {
        loginIdText = find(R.id.mine_information_textId);
        smallChangeText = find(R.id.mine_small_change);
        collectionText = find(R.id.mine_collection);
        historicalOrdersText = find(R.id.mine_historical_orders);
        setUpText = find(R.id.mine_set_up);

        smallChangeText.setOnClickListener(this);
        collectionText.setOnClickListener(this);
        historicalOrdersText.setOnClickListener(this);
        setUpText.setOnClickListener(this);

        smallChangeRefreshBtn = find(R.id.smallChange_Refresh);
        smallChangeRefreshBtn.setOnClickListener(this);


        minePresenter = new MinePresenter(this);//设置登录状态的用户id
        minePresenter.getPersonnelInformation();


        //零钱充值数据返回到此
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts
                .StartActivityForResult(), result -> {
            if (result.getData() != null) {
                String rechargeAmount = result.getData().getStringExtra("data_return_1");

                //将已有的零钱+充值的金额得到总金额
                int amount = Integer.parseInt(rechargeAmount) + Integer.parseInt(smallChange);
                setSmallChangeDatabase(getContext(), String.valueOf(amount));//把总金额存进数据库
                displaySmallChangeOnMine(String.valueOf(amount));//展示我的具体零钱
            }
        });
    }

    //给mine设置初值
    @Override
    public void getPersonnelInformation(PersonnelInformation personnelInformation) {
        loginIdText.setText(personnelInformation.getUsername());
        smallChange = personnelInformation.getSmallChange();//从数据库拿到零钱数的值

        displaySmallChangeOnMine(smallChange);//展示零钱

    }

    /**
     * 展示零钱
     * @param smallChange 零钱
     */
    private void displaySmallChangeOnMine(String smallChange) {
        String str = "我的零钱：" + smallChange;
        smallChangeText.setText(str);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_small_change:
                Intent intentSmallChange = new Intent(getActivity(), SmallChangeActivity.class);
                activityResultLauncher.launch(intentSmallChange);
                break;
            case R.id.mine_collection:
                Intent intentCollect = new Intent(getActivity(), CollectionActivity.class);
                intentCollect.putExtra("username", loginIdText.getText().toString());
                startActivity(intentCollect);
                break;
            case R.id.mine_historical_orders:
                Intent intentHistorical = new Intent(getActivity(), HistoricalOrdersActivity.class);
                startActivity(intentHistorical);
                break;
            case R.id.mine_set_up:
                Intent intentSet = new Intent(getActivity(), SettingActivity.class);
                startActivity(intentSet);
                break;
            case R.id.smallChange_Refresh:
                minePresenter.getPersonnelInformation();
                Toast.makeText(getContext(), "余额已更新！", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
