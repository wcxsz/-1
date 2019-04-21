package com.example.geek.presenter;

import com.example.geek.base.BasePresenter;
import com.example.geek.model.V2EXDetailModel;
import com.example.geek.view.V2EXDetailView;

public class V2EXDetailPresenter extends BasePresenter<V2EXDetailView> {

    private V2EXDetailModel v2EXDetailModel;

    @Override
    protected void initModel() {
        v2EXDetailModel = new V2EXDetailModel();

    }
}
