package com.example.geek.presenter;

import com.example.geek.base.BasePresenter;
import com.example.geek.bean.WeChatBean;
import com.example.geek.model.WeChatModel;
import com.example.geek.net.ResultCallBack;
import com.example.geek.view.MainView;
import com.example.geek.view.WeChatView;

public class WeChatPresenter extends BasePresenter<WeChatView> {

    private WeChatModel weChatModel;

    @Override
    protected void initModel() {
        weChatModel = new WeChatModel();
        list.add(weChatModel);
    }
    public void getData(String string) {
        weChatModel.getData(string, new ResultCallBack<WeChatBean>() {
            @Override
            public void onSuccess(WeChatBean weChatBean) {
                if (mView!=null) {
                    mView.setData(weChatBean);
                }
            }

            @Override
            public void onFail(String msg) {
                if (mView!=null) {
                    mView.setShiBai(msg);
                }
            }
        });
    }

    public void initData(String string) {
        weChatModel.setData(string, new ResultCallBack<WeChatBean>() {
            @Override
            public void onSuccess(WeChatBean weChatBean) {
                if (mView!=null) {
                    mView.initData(weChatBean);
                }
            }

            @Override
            public void onFail(String string) {
                if (mView!=null) {
                    mView.shuo(string);
                }
            }
        });
    }
}
