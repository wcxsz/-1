package com.example.geek.fargment;


import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.geek.R;
import com.example.geek.activity.GoldActivity;
import com.example.geek.adapter.VpGoldAdapter;
import com.example.geek.base.BaseFragment;
import com.example.geek.base.Constants;
import com.example.geek.bean.GoldShowBean;
import com.example.geek.bean.GoldTabBean;
import com.example.geek.fargment.Gold.GoldDetailFragment;
import com.example.geek.presenter.GoldPresenter;
import com.example.geek.view.GoldView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class GoldFragment extends BaseFragment<GoldView, GoldPresenter> implements GoldView {

    @BindView(R.id.tab_gold)
    TabLayout tab_gold;
    @BindView(R.id.iv_gold)
    ImageView iv_gold;
    @BindView(R.id.vp_gold)
    ViewPager vp_gold;
    private ArrayList<GoldShowBean> mList;
    private ArrayList<BaseFragment> mFragments;
    private ArrayList<GoldShowBean> realList;

    @Override
    protected GoldPresenter initPresenter() {
        return new GoldPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold;
    }

    @Override
    protected void initView() {
        realList = new ArrayList<>();
        mList = new ArrayList<>();
        mFragments = new ArrayList<>();
        mpresenter.getDataTab();
    }

    private void setFragments() {
        mFragments.clear();
        realList.clear();
        for (int i = 0; i < mList.size(); i++) {
            GoldShowBean bean = mList.get(i);
            if (bean.isChecked) {
                realList.add(bean);
                mFragments.add(new GoldDetailFragment(bean.cid));
            }
        }

        VpGoldAdapter adapter = new VpGoldAdapter(getChildFragmentManager(),
                mFragments, realList);
        vp_gold.setAdapter(adapter);
        tab_gold.setupWithViewPager(vp_gold);
    }


    @OnClick({R.id.tab_gold, R.id.iv_gold})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_gold:
                go2ShowActivity();
                break;
        }
    }

    private void go2ShowActivity() {
        Intent intent = new Intent(getContext(), GoldActivity.class);
        intent.putExtra(Constants.DATA, mList);
        startActivityForResult(intent, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {//&&resultCode == Activity.RESULT_OK
            mList = (ArrayList<GoldShowBean>) data.getSerializableExtra(Constants.DATA);
            //刷新界面
            setFragments();
        }
    }

    @Override
    public void onSuccessTab(GoldTabBean goldTabBean) {
        List<GoldTabBean.DataBean> data = goldTabBean.getData();
        for (int i = 0; i < data.size(); i++) {
            //mFragments.add(new GoldDetailFragment(data.get(i).getCourseId()+""));
            mList.add(new GoldShowBean(data.get(i).getName(), true, data.get(i).getCourseId() + ""));
        }
        setFragments();
    }

    @Override
    public void onFailTab(String msg) {

    }
}
