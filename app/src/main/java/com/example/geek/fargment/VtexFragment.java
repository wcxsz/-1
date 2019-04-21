package com.example.geek.fargment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.geek.R;
import com.example.geek.activity.V2EXActivity;
import com.example.geek.adapter.VpV2EXAdapter;
import com.example.geek.base.BaseFragment;
import com.example.geek.bean.V2exTabBean;
import com.example.geek.fargment.V2EX.V2EXDetailFragment;
import com.example.geek.presenter.VtexPresenter;
import com.example.geek.view.VtexView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class VtexFragment extends BaseFragment<VtexView, VtexPresenter> implements VtexView{
    private static final String TAG = "V2exFragment";
    private String mUrl = "https://www.v2ex.com/";
    @BindView(R.id.tab_v2ex)
    TabLayout tab_v2ex;
    @BindView(R.id.iv_v2ex)
    ImageView iv_v2ex;
    @BindView(R.id.vp_v2ex)
    ViewPager vp_v2ex;
    private ArrayList<V2exTabBean> listTabV2ex;
    private ArrayList<BaseFragment> listFragment;
    private VpV2EXAdapter adapter;

    @Override
    protected VtexPresenter initPresenter() {
        return new VtexPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_vtex;
    }

    @Override
    protected void initView() {
        listTabV2ex = new ArrayList<>();

        listFragment = new ArrayList<>();
    }

    @Override
    protected void initData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Document document = Jsoup.connect(mUrl).get();
                    Element tabs = document.select("div#Tabs").first();
                    Elements allTabs = tabs.select("a[href]");
                    for (Element element : allTabs) {
                        String linkHref = element.attr("href");
                        String tab = element.text();
                        V2exTabBean v2exTabBean = new V2exTabBean(linkHref, tab, true);
                        listTabV2ex.add(v2exTabBean);
                        listFragment.add(new V2EXDetailFragment(linkHref));
                        //Log.d(TAG, "tabsList: "+v2exTabBean.toString());
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            VpV2EXAdapter vpV2EXAdapter = new VpV2EXAdapter(getChildFragmentManager(), listFragment, listTabV2ex);
                            vp_v2ex.setAdapter(vpV2EXAdapter);
                            tab_v2ex.setupWithViewPager(vp_v2ex);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

        /*protected void initData1() {
        listFragment = new ArrayList<>();
        for (int i = 0; i < listTabV2ex.size(); i++) {
            V2exTabBean v2exTabBean = listTabV2ex.get(i);
            listFragment.add(new V2EXDetailFragment(v2exTabBean.getTab()));
        }
        adapter = new VpV2EXAdapter(getChildFragmentManager(), listFragment, listTabV2ex);
        vp_v2ex.setAdapter(adapter);
        tab_v2ex.setupWithViewPager(vp_v2ex);
    }*/

    @OnClick(R.id.iv_v2ex)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.iv_v2ex:
                Intent intent = new Intent(getContext(), V2EXActivity.class);
                startActivity(intent);
                break;
        }
    }
}
