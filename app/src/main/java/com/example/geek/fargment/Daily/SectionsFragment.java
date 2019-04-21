package com.example.geek.fargment.Daily;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geek.R;
import com.example.geek.adapter.RlvSectionsAdapter;
import com.example.geek.base.BaseFragment;
import com.example.geek.base.BasePresenter;
import com.example.geek.bean.DailySectionsBean;
import com.example.geek.presenter.DailySectionsPreswenter;
import com.example.geek.view.DailySectionsView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SectionsFragment extends BaseFragment<DailySectionsView,DailySectionsPreswenter> implements DailySectionsView{
    @BindView(R.id.rlv_sections)
    RecyclerView rlv_sections;
    private ArrayList<DailySectionsBean.DataBean> list;
    private RlvSectionsAdapter adapter;

    @Override
    protected void initView() {
        list = new ArrayList<>();
        rlv_sections.setLayoutManager(new GridLayoutManager(getContext(),2));
        adapter = new RlvSectionsAdapter(list, getContext());
        rlv_sections.setAdapter(adapter);
    }

    @Override
    protected DailySectionsPreswenter initPresenter() {
        return new DailySectionsPreswenter();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sections;
    }

    @Override
    protected void initData() {
        mpresenter.getData();
    }

    @Override
    public void setData(DailySectionsBean bean) {
        List<DailySectionsBean.DataBean> data = bean.getData();
        list.addAll(data);
        adapter.setList(list);
        adapter.notifyDataSetChanged();
    }
}
