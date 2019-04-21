package com.example.geek.view;

import com.example.geek.base.BaseMvpView;
import com.example.geek.model.DailyNewModel;

public interface DailyView extends BaseMvpView {
    void setData(DailyNewModel bean);
}
