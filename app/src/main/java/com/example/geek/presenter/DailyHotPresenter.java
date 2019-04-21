package com.example.geek.presenter;

import com.example.geek.base.BasePresenter;
import com.example.geek.bean.DailyHotBean;
import com.example.geek.bean.DailyNewBean;
import com.example.geek.model.DailyHotModel;
import com.example.geek.model.DailyNewModel;
import com.example.geek.net.ResultCallBack;
import com.example.geek.view.DailyHotView;
import com.example.geek.view.DailyNewView;

public class DailyHotPresenter extends BasePresenter<DailyHotView> {

    private DailyHotModel dailyHotModel;

    @Override
    protected void initModel() {
        dailyHotModel = new DailyHotModel();
        list.add(dailyHotModel);
    }
    public void getData(){
        dailyHotModel.getData(new ResultCallBack<DailyHotBean>(){
            @Override
            public void onSuccess(DailyHotBean bean) {
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
