package com.example.geek.view;

import com.example.geek.base.BaseMvpView;
import com.example.geek.bean.DailyHotBean;
import com.example.geek.bean.DailyNewBean;

public interface DailyHotView extends BaseMvpView {
    void setData(DailyHotBean bean);
}
