package com.example.geek.fargment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geek.R;
import com.example.geek.base.BaseFragment;
import com.example.geek.presenter.GankPresenter;
import com.example.geek.view.GankView;

/**
 * A simple {@link Fragment} subclass.
 */
public class GankFragment extends BaseFragment<GankView,GankPresenter> {

    @Override
    protected void initView() {

    }

    @Override
    protected GankPresenter initPresenter() {
        return new GankPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank;
    }

}
