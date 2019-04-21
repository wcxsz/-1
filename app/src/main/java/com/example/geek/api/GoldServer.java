package com.example.geek.api;

import com.example.geek.bean.GoldTabBean;
import com.example.geek.bean.GoldTabItemBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface GoldServer {
    //http://www.wanandroid.com/project/tree/json
    //http://www.wanandroid.com/project/list/1/json?cid=294
    public String Url1 = "http://www.wanandroid.com/project/";

    @GET("tree/json")
    Observable<GoldTabBean> getDatatab();

    @GET
    Observable<GoldTabItemBean> getDataItem(@Url String string);

}
