package com.example.geek.base;

import java.util.ArrayList;

public abstract class BasePresenter<V extends BaseMvpView> {
    protected V mView;
    protected ArrayList<BaseModel> list = new ArrayList<>();
    public BasePresenter (){
        initModel();
    }

    protected abstract void initModel();

    public void setmView(V mView) {
        this.mView = mView;
    }
    public void onDestory(){
        mView = null;
        if (list.size()>0){
            for (BaseModel model: list) {
                model.onDestory();
            }
        }
    }
}
