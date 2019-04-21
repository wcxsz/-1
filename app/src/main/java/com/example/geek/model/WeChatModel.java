package com.example.geek.model;

import com.example.geek.api.WechatServer;
import com.example.geek.base.BaseModel;
import com.example.geek.bean.WeChatBean;
import com.example.geek.net.ResultCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeChatModel extends BaseModel {
    public void getData(String string, final ResultCallBack<WeChatBean> callBacks) {
        Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .baseUrl(WechatServer.STRING)
                        .build();
        WechatServer myApi = retrofit.create(WechatServer.class);
                Observable<WeChatBean> call = myApi.weChatCall(string);
                call.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<WeChatBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(WeChatBean value) {
                                if (value != null) {
                                    callBacks.onSuccess(value);
                                } else {
                                    callBacks.onFail("失败");
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                callBacks.onFail(e.getMessage());
                            }

                            @Override
                            public void onComplete() {

                            }
                        });
    }

    public void setData(String string, final ResultCallBack<WeChatBean> callBacks) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(WechatServer.STRING)
                .build();
        WechatServer myApi = retrofit.create(WechatServer.class);
        Observable<WeChatBean> call = myApi.shouSuoCall(string);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeChatBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WeChatBean value) {
                        if (value != null) {
                            callBacks.onSuccess(value);
                        } else {
                            callBacks.onFail("失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        callBacks.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
