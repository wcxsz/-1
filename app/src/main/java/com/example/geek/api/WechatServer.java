package com.example.geek.api;

import com.example.geek.bean.WeChatBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface WechatServer {
    //http://api.tianapi.com/wxnew/?key=52b7ec3471ac3bec6846577e79f20e4c&num=10&page=1
    String STRING = "http://api.tianapi.com/wxnew/";
    @GET
    Observable<WeChatBean> weChatCall(@Url String url);
    @GET
    Observable<WeChatBean> shouSuoCall(@Url String url);
}
