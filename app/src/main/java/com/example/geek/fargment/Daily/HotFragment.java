package com.example.geek.fargment.Daily;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geek.R;
import com.example.geek.adapter.RlvHotAdapter;
import com.example.geek.base.BaseFragment;
import com.example.geek.base.BasePresenter;
import com.example.geek.bean.DailyHotBean;
import com.example.geek.presenter.DailyHotPresenter;
import com.example.geek.view.DailyHotView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends BaseFragment<DailyHotView,DailyHotPresenter> implements DailyHotView {
    @BindView(R.id.rlv_hot)
    RecyclerView rlv_hot;
    private ArrayList<DailyHotBean.RecentBean> list;
    private RlvHotAdapter adapter;

    @Override
    protected void initView() {
        list = new ArrayList<>();
        rlv_hot.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RlvHotAdapter(list, getContext());
        rlv_hot.setAdapter(adapter);
    }

    @Override
    protected DailyHotPresenter initPresenter() {
        return new DailyHotPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initData() {
        super.initData();
        mpresenter.getData();
    }

    @Override
    public void setData(DailyHotBean bean) {
        List<DailyHotBean.RecentBean> recent = bean.getRecent();
        list.addAll(recent);
        adapter.setList(list);
        adapter.notifyDataSetChanged();
    }
}
