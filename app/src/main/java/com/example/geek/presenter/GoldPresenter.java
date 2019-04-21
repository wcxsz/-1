package com.example.geek.presenter;

import com.example.geek.base.BasePresenter;
import com.example.geek.bean.GoldTabBean;
import com.example.geek.bean.GoldTabItemBean;
import com.example.geek.model.GoldTabModel;
import com.example.geek.net.ResultCallBack;
import com.example.geek.view.GankView;
import com.example.geek.view.GoldView;
import com.example.geek.view.MainView;

public class GoldPresenter extends BasePresenter<GoldView> {

    private GoldTabModel goldTabModel;

    @Override
    protected void initModel() {
        goldTabModel = new GoldTabModel();
        list.add(goldTabModel);
    }
    public void getDataTab(){

        goldTabModel.getDataTab(new ResultCallBack<GoldTabBean>() {
            @Override
            public void onSuccess(GoldTabBean goldTabBean) {
                mView.onSuccessTab(goldTabBean);
            }

            @Override
            public void onFail(String msg) {
                mView.onFailTab(msg);
            }
        });
    }

}
