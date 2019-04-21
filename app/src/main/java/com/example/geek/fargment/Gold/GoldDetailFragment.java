package com.example.geek.fargment.Gold;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.geek.R;
import com.example.geek.adapter.RlvGoldDetailAdapter;
import com.example.geek.base.BaseFragment;
import com.example.geek.base.Constants;
import com.example.geek.bean.GoldTabItemBean;
import com.example.geek.presenter.EmptyPresenter;
import com.example.geek.presenter.GoldDetailPresenter;
import com.example.geek.view.EmptyView;
import com.example.geek.view.GoldDetailView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@SuppressLint("ValidFragment")
public class GoldDetailFragment extends BaseFragment<GoldDetailView,GoldDetailPresenter> implements GoldDetailView{

    @BindView(R.id.rlv_gold_detail)
    RecyclerView rlv_gold_detail;

    /*public static GoldDetailFragment newInstance(String text){
        GoldDetailFragment fragment = new GoldDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.DATA,text);
        fragment.setArguments(bundle);
        return fragment;
    }*/
    private String cid;
    private ArrayList<GoldTabItemBean.DataBean.DatasBean> list;
    private RlvGoldDetailAdapter adapter;

    @SuppressLint("ValidFragment")
    public GoldDetailFragment(String cid) {
        this.cid = cid;
    }

    @Override
    protected GoldDetailPresenter initPresenter() {
        return new GoldDetailPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold_detail;
    }
    @Override
    protected void initView() {
        list = new ArrayList<>();
        rlv_gold_detail.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RlvGoldDetailAdapter(list, getContext());
        rlv_gold_detail.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        mpresenter.getDataItem("list/1/json?cid="+cid);
    }

    @Override
    public void onSuccessTabItem(GoldTabItemBean goldTabBean) {
        List<GoldTabItemBean.DataBean.DatasBean> datas = goldTabBean.getData().getDatas();
        list.addAll(datas);
        adapter.setList(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailTabItem(String msg) {

    }
}
