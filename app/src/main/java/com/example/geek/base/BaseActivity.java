package com.example.geek.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.geek.presenter.MainPresenter;

import butterknife.ButterKnife;

public abstract class BaseActivity<V extends BaseMvpView , P extends BasePresenter> extends AppCompatActivity {
    protected P mpresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mpresenter = initPresenter();
        initView();
        if (mpresenter != null){
            mpresenter.setmView((V) this);
        }
        initListener();
    }

    protected abstract void initListener();

    protected abstract void initView();

    protected abstract P initPresenter();

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mpresenter.onDestory();
        mpresenter = null;
    }
}
