package com.example.geek.model;

import com.example.geek.api.DailyServer;
import com.example.geek.base.BaseModel;
import com.example.geek.bean.DailyNewBean;
import com.example.geek.bean.DailyNewCalendarBean;
import com.example.geek.net.BaseObserver;
import com.example.geek.net.HttpUtils;
import com.example.geek.net.ResultCallBack;
import com.example.geek.net.RxUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DailyNewModel extends BaseModel {

    public void getData(final ResultCallBack<DailyNewBean> resultCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DailyServer.Url1)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        DailyServer dailyServer = retrofit.create(DailyServer.class);
        Observable<DailyNewBean> data = dailyServer.getData();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DailyNewBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(DailyNewBean dailyNewBean) {
                        resultCallBack.onSuccess(dailyNewBean);
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
    public void getData1(String string,final ResultCallBack<DailyNewCalendarBean> resultCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DailyServer.Url1)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        DailyServer dailyServer = retrofit.create(DailyServer.class);
        Observable<DailyNewCalendarBean> data = dailyServer.getData3(string);
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DailyNewCalendarBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(DailyNewCalendarBean dailyNewCalendarBean) {
                        resultCallBack.onSuccess(dailyNewCalendarBean);
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
