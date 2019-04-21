package com.example.geek.view;

import com.example.geek.base.BaseMvpView;
import com.example.geek.bean.GoldTabItemBean;

public interface GoldDetailView extends BaseMvpView {
    void onSuccessTabItem(GoldTabItemBean goldTabBean);

    void onFailTabItem(String msg);
}
