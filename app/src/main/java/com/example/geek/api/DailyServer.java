package com.example.geek.api;

import com.example.geek.bean.DailyHotBean;
import com.example.geek.bean.DailyNewBean;
import com.example.geek.bean.DailyNewCalendarBean;
import com.example.geek.bean.DailySectionsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface DailyServer {
    public String Url1 = "http://news-at.zhihu.com/api/4/";

    @GET("news/latest")
    Observable<DailyNewBean> getData();

    @GET("sections")
    Observable<DailySectionsBean> getData1();

    @GET("news/hot")
    Observable<DailyHotBean> getData2();

    @GET()
    Observable<DailyNewCalendarBean> getData3(@Url String string);
}
