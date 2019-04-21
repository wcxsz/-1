package com.example.geek.fargment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geek.R;
import com.example.geek.base.BaseFragment;
import com.example.geek.presenter.SetPresenter;
import com.example.geek.view.SetView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetFragment extends BaseFragment<SetView,SetPresenter> {


    public SetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set, container, false);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected SetPresenter initPresenter() {
        return new SetPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_set;
    }

}
