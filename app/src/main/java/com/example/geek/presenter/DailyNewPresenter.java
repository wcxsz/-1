package com.example.geek.presenter;

import com.example.geek.base.BasePresenter;
import com.example.geek.bean.DailyNewBean;
import com.example.geek.bean.DailyNewCalendarBean;
import com.example.geek.model.DailyNewModel;
import com.example.geek.net.ResultCallBack;
import com.example.geek.view.DailyNewView;
import com.example.geek.view.DailyView;

public class DailyNewPresenter extends BasePresenter<DailyNewView> {

    private DailyNewModel dailyModel;

    @Override
    protected void initModel() {
        dailyModel = new DailyNewModel();
        list.add(dailyModel);
    }
    public void getData(){
        dailyModel.getData(new ResultCallBack<DailyNewBean>(){
            @Override
            public void onSuccess(DailyNewBean bean) {
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
    public void getData1(String string){
        dailyModel.getData1(string,new ResultCallBack<DailyNewCalendarBean>(){
            @Override
            public void onSuccess(DailyNewCalendarBean bean) {
                if (bean!= null){
                    if (mView != null){
                        mView.setDataCalendar(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
