package com.example.geek.fargment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geek.R;
import com.example.geek.base.BaseFragment;
import com.example.geek.presenter.AboutPresenter;
import com.example.geek.view.AboutView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends BaseFragment<AboutView,AboutPresenter> {

    @Override
    protected void initView() {

    }

    @Override
    protected AboutPresenter initPresenter() {
        return new AboutPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }

}
