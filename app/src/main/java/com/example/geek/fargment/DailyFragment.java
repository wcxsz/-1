package com.example.geek.fargment;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.geek.R;
import com.example.geek.adapter.VpZhihuAdapter;
import com.example.geek.base.BaseFragment;
import com.example.geek.fargment.Daily.DailyNewFragment;
import com.example.geek.fargment.Daily.HotFragment;
import com.example.geek.fargment.Daily.SectionsFragment;
import com.example.geek.presenter.DailyPresenter;
import com.example.geek.view.DailyView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
/*周林  1808B*/
public class DailyFragment extends BaseFragment<DailyView,DailyPresenter> {
    @BindView(R.id.tab_daily)
    TabLayout mTabLayout;
    @BindView(R.id.vp_daily)
    ViewPager mVp;
    private ArrayList<Integer> mTitles;
    private ArrayList<BaseFragment> mFragments;
    @Override
    protected DailyPresenter initPresenter() {
        return new DailyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily;
    }
    @Override
    protected void initView() {
        initTitles();
        initFragments();
        VpZhihuAdapter adapter = new VpZhihuAdapter(getContext(),
                getChildFragmentManager(), mFragments, mTitles);
        mVp.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mVp);
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(new DailyNewFragment());
        mFragments.add(new SectionsFragment());
        mFragments.add(new HotFragment());
    }

    private void initTitles() {
        mTitles = new ArrayList<>();
       mTitles.add(R.string.dailyNews);
        mTitles.add(R.string.sections);
        mTitles.add(R.string.hot);
    }
}
