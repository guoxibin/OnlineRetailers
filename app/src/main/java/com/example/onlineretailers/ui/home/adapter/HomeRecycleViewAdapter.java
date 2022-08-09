package com.example.onlineretailers.ui.home.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.onlineretailers.Bean.Goods;
import com.example.onlineretailers.R;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.List;

public class HomeRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private List<Goods> data;
    private RecyclerView recyclerView;
    private Context context;
    private ItemClickListener itemClickListener;


    public HomeRecycleViewAdapter(List<Goods> data, RecyclerView recyclerView, Context context) {
        this.data = data;
        this.context = context;
        this.recyclerView = recyclerView;
    }

    /**
     * 通知数据已发生改变
     * @param data 改变的数据
     */
    @SuppressLint("NotifyDataSetChanged")
    public void setGoods(List<Goods> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    //一个view
    static class SingleViewHolder extends RecyclerView.ViewHolder {
        public SingleViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    //两个view
    static class MultiViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;

        public MultiViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
            imageView = itemView.findViewById(R.id.image);
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(viewType, parent, false);
        view.setOnClickListener(this);//为每个view注册一个点击监听事件
        if (viewType == R.layout.home_recycler_text_image) {
            return new MultiViewHolder(view);
        }
        return new SingleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Goods goods = data.get(position);
        int viewType = getItemViewType(position);
        //对不同viewType进行判断并处理
        switch (viewType) {
            case R.layout.home_recycler_banner:
                ((Banner)holder.itemView).setAdapter(new BannerImageAdapter<>(goods.getBanners()) {
                            @Override
                            public void onBindView(BannerImageHolder holder, String data, int position, int size) {
                                //图片加载自己实现
                                Glide.with(holder.itemView)
                                        .load(data)
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .apply(RequestOptions.centerCropTransform()) //圆形图片
                                        .into(holder.imageView);
                            }
                        })
                        .addBannerLifecycleObserver((LifecycleOwner) context)//添加生命周期观察者
                        .setIndicator(new CircleIndicator(context));
                break;
            case R.layout.home_recycler_image:
                Glide.with(holder.itemView)
                        .load(goods.getImageUrl())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .apply(RequestOptions.centerCropTransform())
                        .into((ImageView) holder.itemView);
                break;
            case R.layout.home_recycler_text:
                ((TextView)holder.itemView).setText(goods.getText());
                break;
            case R.layout.home_recycler_text_image:
                MultiViewHolder multiViewHolder = (MultiViewHolder)holder;
                multiViewHolder.textView.setText(goods.getText());
                Glide.with(holder.itemView)
                        .load(goods.getImageUrl())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .apply(RequestOptions.centerCropTransform())
                        .into(multiViewHolder.imageView);
                break;
        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    /**
     * 为各种不同的view设置不同的viewType
     * @param position 当前位置
     * @return 不同的viewType
     */
    @Override
    public int getItemViewType(int position) {
        Goods goods = data.get(position);
        if (goods.getBanners() != null) {
            //banner轮播图
            return R.layout.home_recycler_banner;
        } else if (goods.getImageUrl() == null) {
            //显示文字
            return R.layout.home_recycler_text;
        } else if (goods.getText() == null){
            //显示图片
            return R.layout.home_recycler_image;
        } else {
            //显示文字与图片
            return R.layout.home_recycler_text_image;
        }
    }

    @Override
    public void onClick(View v) {
        if (itemClickListener != null) {
            int position = recyclerView.getChildLayoutPosition(v);
            itemClickListener.onItemOnClick(data.get(position));//把点击的item的位置传过去
        }
    }

    public void setItemClickListener(ItemClickListener onItemClickListener) {
        this.itemClickListener = onItemClickListener;
    }




    public interface ItemClickListener {
        void onItemOnClick(Goods goods);
    }

}
