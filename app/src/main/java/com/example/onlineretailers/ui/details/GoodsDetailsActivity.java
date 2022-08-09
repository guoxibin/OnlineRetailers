package com.example.onlineretailers.ui.details;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.onlineretailers.Bean.DetailData;
import com.example.onlineretailers.R;
import com.example.onlineretailers.ui.base.BaseActivity;
import com.example.onlineretailers.ui.details.shoppinglist.MyBottomSheetDialogFragment;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

public class GoodsDetailsActivity extends BaseActivity implements
        View.OnClickListener, GoodsDetailContact.IGoodsDetailView{


    public static final String GOODS_ID = "goods_id";
    private GoodsDetailsPresenter goodsDetailsPresenter;
    private Toolbar toolbar;
    private TextView detail_Content,
    detail_buyingQuick, detail_addToShoppingCart,
    detail_kf, detail_collection, detail_shoppingCart,
    detail_goods_present_price, detail_goods_origin_price,
            detail_goods_name, detail_goods_specification;
    private Banner banner;
    private DetailData detailData;//返回的数据类


    @Override
    protected int getLayoutInflate() {
        return R.layout.activity_goods_details;
    }

    @Override
    protected void initView() {
        initGoodsDetail();//商品详情页初始化
        initGoodsDetailsBottomView();//商品详情页底部导航栏初始化

        //点击的子项布局的id
        int goodsId = getIntent().getIntExtra(GOODS_ID, 0);
        goodsDetailsPresenter = new GoodsDetailsPresenter(this);
        goodsDetailsPresenter.getGoodsDetail(goodsId);//把点击的item的id传进去
    }

    /**
     * 初始化商品详情页
     */
    private void initGoodsDetail() {
        toolbar = findViewById(R.id.detail_toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());//toolbar返回键触发finish()方法
        banner = findViewById(R.id.detail_banner);
        detail_Content = findViewById(R.id.detail_content);//商品详情内容
        detail_goods_origin_price = findViewById(R.id.detail_goods_origin_price);
        detail_goods_present_price = findViewById(R.id.detail_goods_present_price);
        detail_goods_specification = findViewById(R.id.detail_goods_specification);
        detail_goods_name = findViewById(R.id.detail_goods_name);
    }

    /**
     * 初始化商品详情页底部导航栏
     */
    private void initGoodsDetailsBottomView() {
        detail_buyingQuick = findViewById(R.id.detail_buy_now);
        detail_addToShoppingCart = findViewById(R.id.detail_add_to_shoppingCar);
        detail_kf = findViewById(R.id.detail_kf);
        detail_collection = findViewById(R.id.detail_collection);
        detail_shoppingCart = findViewById(R.id.detail_shoppingCar);

        detail_addToShoppingCart.setOnClickListener(this);
        detail_buyingQuick.setOnClickListener(this);
        detail_kf.setOnClickListener(this);
        detail_collection.setOnClickListener(this);
        detail_shoppingCart.setOnClickListener(this);
    }

    /**
     * 底部打开菜单栏的点击事件
     * @param v 点击的view
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail_add_to_shoppingCar:
            case R.id.detail_buy_now: //立即购买的底部菜单栏
                MyBottomSheetDialogFragment myBottomSheetDialogFragment = new MyBottomSheetDialogFragment();
                myBottomSheetDialogFragment.setDetailData(detailData);
                myBottomSheetDialogFragment.show(getSupportFragmentManager()
                        ,"MyBottomSheetDialogFragment");
                break;
            case R.id.detail_collection:
                if (detailData != null) {
                    goodsDetailsPresenter.setDetailCollection(detailData);//存储收藏数据
                    Toast.makeText(this, "已收藏", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * 拿到数据后设置给view
     * @param detailData 返回的商品信息
     */
    @Override
    public void getGoodsDetailSuccess(DetailData detailData) {
        toolbar.setTitle(detailData.getName());
        //加载banner图
        banner.setAdapter(new BannerImageAdapter<>(detailData.getBanners()) {
                    @Override
                    public void onBindView(BannerImageHolder holder, String data, int position, int size) {
                        Glide.with(holder.itemView)
                                .load(data)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                                .into(holder.imageView);
                        }
                    })
                .addBannerLifecycleObserver(this)//添加生命周期观察者
                .setIndicator(new CircleIndicator(this));

        detail_goods_name.setText(detailData.getName());//商品名称
        detail_goods_origin_price.setText(detailData.getOrigin_price());//原价
        detail_goods_present_price.setText(detailData.getPresent_price());//现价

        String text = detailData.getSpecification().getSize()
                + "\n" + detailData.getSpecification().getWeight()
                + "\n" + detailData.getSpecification().getOther_specifications();
        detail_goods_specification.setText(text);//参数

        detail_Content.setText(detailData.getContent());//内容详情

        this.detailData = detailData;
    }

    @Override
    public void getGoodsDetailError(Throwable throwable) {
        Toast.makeText(this,"获取商品详情失败",Toast.LENGTH_SHORT).show();
    }

}
