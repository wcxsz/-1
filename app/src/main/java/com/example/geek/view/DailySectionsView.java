package com.example.geek.view;

import com.example.geek.base.BaseMvpView;
import com.example.geek.bean.DailyNewBean;
import com.example.geek.bean.DailySectionsBean;

public interface DailySectionsView extends BaseMvpView {
    void setData(DailySectionsBean bean);
}
