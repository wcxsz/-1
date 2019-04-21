package com.example.geek.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V extends BaseMvpView , P extends BasePresenter>
        extends Fragment implements BaseMvpView {
    protected P mpresenter;
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayoutId(), null);
        mUnbinder = ButterKnife.bind(this, inflate);
        mpresenter = initPresenter();
        initView();
        initData();
        if (mpresenter != null) {
            mpresenter.setmView((V) this);
        }
        return inflate;
    }
    protected void initData() {

    }

    protected abstract void initView();

    protected abstract P initPresenter();

    protected abstract int getLayoutId();

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        mpresenter.onDestory();
        mpresenter = null;
    }
}
