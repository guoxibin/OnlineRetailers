package com.example.onlineretailers.ui.home.adapter;

import androidx.recyclerview.widget.GridLayoutManager;

import com.example.onlineretailers.Bean.Goods;

import java.util.List;

public class HomeSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private final List<Goods> data;

    public HomeSpanSizeLookup(List<Goods> data) {
        this.data = data;
    }

    @Override
    public int getSpanSize(int position) {
        return data.get(position).getSpanSize();
    }


    public void setSpanSize(List<Goods> goods) {
        data.clear();
        data.addAll(goods);

    }
}
