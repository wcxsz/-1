package com.example.geek.view;

import com.example.geek.base.BaseMvpView;
import com.example.geek.bean.GoldTabBean;
import com.example.geek.bean.GoldTabItemBean;

public interface GoldView extends BaseMvpView {
    void onSuccessTab(GoldTabBean goldTabBean);

    void onFailTab(String msg);


}
