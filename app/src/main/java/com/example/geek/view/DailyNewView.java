package com.example.geek.view;

import com.example.geek.base.BaseMvpView;
import com.example.geek.bean.DailyNewBean;
import com.example.geek.bean.DailyNewCalendarBean;
import com.example.geek.model.DailyNewModel;

public interface DailyNewView extends BaseMvpView {
    void setData(DailyNewBean bean);

    void setDataCalendar(DailyNewCalendarBean bean);

}
