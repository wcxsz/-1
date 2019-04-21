package com.example.geek.presenter;

import com.example.geek.base.BasePresenter;
import com.example.geek.bean.DailySectionsBean;
import com.example.geek.model.DailySectionsModel;
import com.example.geek.net.ResultCallBack;
import com.example.geek.view.DailySectionsView;

public class DailySectionsPreswenter extends BasePresenter<DailySectionsView> {

    private DailySectionsModel dailySectionsModel;

    @Override
    protected void initModel() {
        dailySectionsModel = new DailySectionsModel();
        list.add(dailySectionsModel);
    }
    public void getData(){
        dailySectionsModel.getData(new ResultCallBack<DailySectionsBean>() {
            @Override
            public void onSuccess(DailySectionsBean bean) {
                if (bean!= null){
                    if (mView != null){
                        mView.setData(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
