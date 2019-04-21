package com.example.geek.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;

import com.example.geek.R;
import com.example.geek.adapter.RlvGold_Adtivity_Adapter;
import com.example.geek.base.BaseActivity;
import com.example.geek.base.Constants;
import com.example.geek.bean.GoldShowBean;
import com.example.geek.presenter.EmptyPresenter;
import com.example.geek.view.EmptyView;
import com.example.geek.widjet.SimpleTouchHelperCallBack;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoldActivity extends BaseActivity<EmptyView, EmptyPresenter>implements EmptyView {

    @BindView(R.id.iv_gold_activity)
    ImageView iv_gold_activity;
    @BindView(R.id.toolbar_gold_activity)
    Toolbar toolbar_gold_activity;
    @BindView(R.id.rlv_gold_activity)
    RecyclerView rlv_gold_activity;
    private ArrayList<GoldShowBean> mList;
    private RlvGold_Adtivity_Adapter adapter;
    private Intent intent;

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gold;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        intent = getIntent();
        mList = (ArrayList<GoldShowBean>) intent.getSerializableExtra(Constants.DATA);
        setSupportActionBar(toolbar_gold_activity);
        rlv_gold_activity.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RlvGold_Adtivity_Adapter(mList, this);
        rlv_gold_activity.setAdapter(adapter);
        SimpleTouchHelperCallBack simpleTouchHelperCallBack = new SimpleTouchHelperCallBack(adapter);
        simpleTouchHelperCallBack.setSwipeEnable(false);
        ItemTouchHelper helper = new ItemTouchHelper(simpleTouchHelperCallBack);
        helper.attachToRecyclerView(rlv_gold_activity);
    }

    @OnClick(R.id.iv_gold_activity)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_gold_activity:
                finishAct();
                break;
        }
    }

    private void finishAct() {
       // Intent intent = getIntent();
        intent.putExtra(Constants.DATA, mList);
        setResult(RESULT_OK,intent);
        finish();
    }
    @Override
    public void onBackPressed() {
        finishAct();
    }
}
