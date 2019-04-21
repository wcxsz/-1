package com.example.geek.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.geek.R;
import com.example.geek.base.BaseActivity;
import com.example.geek.base.BaseFragment;
import com.example.geek.presenter.EmptyPresenter;
import com.example.geek.view.EmptyView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class V2EXActivity extends BaseActivity<EmptyView,EmptyPresenter> implements EmptyView{
    private static final String TAG = "V2EXActivity";
    private String mUrl = "https://www.v2ex.com/";
    @BindView(R.id.tv_v2ex)
    TextView mTvV2ex;
    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_v2_ex;
    }
    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {
        new Thread(){
            @Override
            public void run() {
                try {
                    Document dco = Jsoup.connect(mUrl).get();
                    Elements first = dco.select("div.cell");
                    for (Element element: first) {
                        Element select = element.select("table tbody tr td span.fade").first();
                        String fade = select.attr("fade");
                        Log.d(TAG,"fade:"+fade);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


}
