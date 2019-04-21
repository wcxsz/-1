package com.example.geek.presenter;

import com.example.geek.base.BasePresenter;
import com.example.geek.bean.GoldTabItemBean;
import com.example.geek.model.GoldDetailModel;
import com.example.geek.net.ResultCallBack;
import com.example.geek.view.GoldDetailView;

public class GoldDetailPresenter extends BasePresenter<GoldDetailView> {

    private GoldDetailModel goldDetailModel;

    @Override
    protected void initModel() {
        goldDetailModel = new GoldDetailModel();
        list.add(goldDetailModel);
    }
    public void getDataItem(String string){
        goldDetailModel.getDataItem(string, new ResultCallBack<GoldTabItemBean>() {
            @Override
            public void onSuccess(GoldTabItemBean goldTabBean) {
                mView.onSuccessTabItem(goldTabBean);
            }

            @Override
            public void onFail(String msg) {
                mView.onFailTabItem(msg);
            }
        });
    }
}
