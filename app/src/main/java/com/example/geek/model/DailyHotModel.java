package com.example.geek.model;

import com.example.geek.api.DailyServer;
import com.example.geek.base.BaseModel;
import com.example.geek.bean.DailyHotBean;
import com.example.geek.bean.DailyNewBean;
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

public class DailyHotModel extends BaseModel {

    public void getData(final ResultCallBack<DailyHotBean> resultCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DailyServer.Url1)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        DailyServer dailyServer = retrofit.create(DailyServer.class);
        Observable<DailyHotBean> data2 = dailyServer.getData2();
        data2.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DailyHotBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(DailyHotBean dailyHotBean) {
                        resultCallBack.onSuccess(dailyHotBean);
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
