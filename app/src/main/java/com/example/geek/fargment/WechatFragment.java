package com.example.geek.fargment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geek.R;
import com.example.geek.adapter.RlvWeChatAdapter;
import com.example.geek.base.BaseFragment;
import com.example.geek.bean.WeChatBean;
import com.example.geek.presenter.WeChatPresenter;
import com.example.geek.view.WeChatView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class WechatFragment extends BaseFragment<WeChatView, WeChatPresenter> implements WeChatView{
    private static final String TAG = "WeChatFragment";
    @BindView(R.id.rlv_wechat)
    RecyclerView rlv_wechat;
    private ArrayList<WeChatBean.NewslistBean> list;
    private RlvWeChatAdapter adapter;
    @Override
    protected WeChatPresenter initPresenter() {
        return new WeChatPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        list = new ArrayList<>();
        adapter = new RlvWeChatAdapter(list,getContext());
        rlv_wechat.setAdapter(adapter);
        rlv_wechat.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void initData() {
        mpresenter.getData("?key=52b7ec3471ac3bec6846577e79f20e4c&num=10&page=1");
    }

    @Override
    public void setData(WeChatBean weChatBean) {
        if (weChatBean!=null&&weChatBean.getNewslist()!=null) {
            list.addAll(weChatBean.getNewslist());
            adapter.setList(list);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void setShiBai(String string) {
        Log.e(TAG, "setShiBai: "+string );
    }

    @Override
    public void initData(WeChatBean weChatBean) {
        list.clear();
        if (weChatBean!=null&&weChatBean.getNewslist()!=null) {
            list.addAll(weChatBean.getNewslist());
            adapter.setList(list);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void shuo(String string) {
        Log.e(TAG, "shuo: "+string);
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void init(String url){
        //Log.e("mm",url );
        mpresenter.initData("?key=52b7ec3471ac3bec6846577e79f20e4c&num=10&page=1&word="+url);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
