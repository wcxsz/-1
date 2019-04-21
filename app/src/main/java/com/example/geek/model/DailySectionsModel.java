package com.example.geek.model;

import android.util.Log;

import com.example.geek.api.DailyServer;
import com.example.geek.base.BaseModel;
import com.example.geek.bean.DailyNewBean;
import com.example.geek.bean.DailySectionsBean;
import com.example.geek.net.BaseObserver;
import com.example.geek.net.HttpUtils;
import com.example.geek.net.ResultCallBack;
import com.example.geek.net.RxUtils;

import java.util.Queue;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DailySectionsModel extends BaseModel {

    public void getData(final ResultCallBack<DailySectionsBean> resultCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DailyServer.Url1)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        DailyServer dailyServer = retrofit.create(DailyServer.class);
        Observable<DailySectionsBean> data1 = dailyServer.getData1();
        data1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DailySectionsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(DailySectionsBean dailySectionsBean) {
                        resultCallBack.onSuccess(dailySectionsBean);

                    }

                    @Override
                    public void onError(Throwable e) {
                        resultCallBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
