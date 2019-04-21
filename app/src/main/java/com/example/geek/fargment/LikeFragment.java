package com.example.geek.fargment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geek.R;
import com.example.geek.base.BaseFragment;
import com.example.geek.presenter.LikePresenter;
import com.example.geek.view.LikeView;

/**
 * A simple {@link Fragment} subclass.
 */
public class LikeFragment extends BaseFragment<LikeView,LikePresenter> {

    @Override
    protected void initView() {

    }

    @Override
    protected LikePresenter initPresenter() {
        return new LikePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_like;
    }

}
