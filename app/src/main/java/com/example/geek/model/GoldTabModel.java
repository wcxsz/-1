package com.example.geek.model;

import com.example.geek.api.GoldServer;
import com.example.geek.base.BaseModel;
import com.example.geek.bean.GoldTabBean;
import com.example.geek.bean.GoldTabItemBean;
import com.example.geek.net.ResultCallBack;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoldTabModel extends BaseModel {
    public void getDataTab( final ResultCallBack<GoldTabBean> resultCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GoldServer.Url1)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        GoldServer goldServer = retrofit.create(GoldServer.class);
        Observable<GoldTabBean> datatab = goldServer.getDatatab();
        datatab.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GoldTabBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(GoldTabBean goldTabBean) {
                        resultCallBack.onSuccess(goldTabBean);
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
