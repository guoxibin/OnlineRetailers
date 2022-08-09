package com.example.onlineretailers.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    private View contentView;


    protected abstract int getLayoutInflate();
    protected abstract void initView();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(getLayoutInflate(), container, false);
        initView();//初始化view
        return contentView;
    }


    protected <T extends View> T find(@IdRes int id) {
        return contentView.findViewById(id);
    }
}
