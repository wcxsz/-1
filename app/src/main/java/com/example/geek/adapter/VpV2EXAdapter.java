package com.example.geek.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.geek.base.BaseFragment;
import com.example.geek.bean.GoldShowBean;
import com.example.geek.bean.V2exTabBean;

import java.util.ArrayList;

/**
 * @author xts
 *         Created by asus on 2019/4/17.
 *         在viewpager不需要的Fragment需要销毁时,生命周期不一样,
 *         FragmentPagerAdapter:onDestoryView()
 *         FragmentStatePagerAdapter:onDetach();取消关联
 */

public class VpV2EXAdapter extends FragmentStatePagerAdapter {
    private ArrayList<BaseFragment> mFragments;
    private ArrayList<V2exTabBean> mTitles;
    private ArrayList<String> mNewTitles = new ArrayList<>();

    public VpV2EXAdapter(FragmentManager fm,
                         ArrayList<BaseFragment> fragments,
                         ArrayList<V2exTabBean> titles) {
        super(fm);
        mFragments = fragments;
        mTitles = titles;

        for (int i = 0; i < mTitles.size(); i++) {
            V2exTabBean bean = mTitles.get(i);
            if (bean.isChecked()){
                mNewTitles.add(bean.getTab());
            }
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mNewTitles.get(position);
    }
}
