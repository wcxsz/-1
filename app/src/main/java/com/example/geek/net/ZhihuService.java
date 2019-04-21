package com.example.geek.net;

import com.example.geek.bean.DailyNewBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author xts
 *         Created by asus on 2019/4/17.
 */

public interface ZhihuService {
    String sBaseUrl = "http://news-at.zhihu.com/api/4/";

    @GET("news/latest")
    Observable<DailyNewBean> getLastDailyNews();
}
