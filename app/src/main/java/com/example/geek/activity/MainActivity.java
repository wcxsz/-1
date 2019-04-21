package com.example.geek.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.geek.R;
import com.example.geek.base.BaseActivity;
import com.example.geek.fargment.AboutFragment;
import com.example.geek.fargment.DailyFragment;
import com.example.geek.fargment.GankFragment;
import com.example.geek.fargment.GoldFragment;
import com.example.geek.fargment.LikeFragment;
import com.example.geek.fargment.SetFragment;
import com.example.geek.fargment.VtexFragment;
import com.example.geek.fargment.WechatFragment;
import com.example.geek.presenter.MainPresenter;
import com.example.geek.view.MainView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainView,MainPresenter>
        implements MainView{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fl)
    FrameLayout fl;
    @BindView(R.id.nv)
    NavigationView nv;
    @BindView(R.id.dl)
    DrawerLayout dl;
    @BindView(R.id.search_view)
    MaterialSearchView mSearchView;
    private ArrayList<Fragment> fragments;
    private ArrayList<Integer> integers;
    private FragmentManager manager;
    private final int TYPE_ZHIHU = 0;
    private final int TYPE_WECHAT = 1;
    private final int TYPE_GANK = 2;
    private final int TYPE_GOLD = 3;
    private final int TYPE_V2EX = 4;
    private final int TYPE_COLLECT = 5;
    private final int TYPE_SETTINGS = 6;
    private final int TYPE_ABOUT = 7;
    private int mLastFragmentPosition;
    private MenuItem mSearchItem;

    /*周林  1808D*/


    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;

    }



    protected void initView() {
        manager = getSupportFragmentManager();
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, dl, toolbar, R.string.about, R.string.about);
        actionBarDrawerToggle.getDrawerArrowDrawable();
        dl.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        initFragment();
        initTitle();
        addZhihuDailyNewsFragment();
    }
    private void addZhihuDailyNewsFragment() {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fl,fragments.get(0));
        transaction.commit();
        toolbar.setTitle(integers.get(0));
    }
    private void initTitle() {
        integers = new ArrayList<>();
        integers.add(R.id.daily);
        integers.add(R.id.wechat);
        integers.add(R.id.gank);
        integers.add(R.id.gold);
        integers.add(R.id.vtex);
        integers.add(R.id.like);
        integers.add(R.id.setting);
        integers.add(R.id.about);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new DailyFragment());
        fragments.add(new WechatFragment());
        fragments.add(new GankFragment());
        fragments.add(new GoldFragment());
        fragments.add(new VtexFragment());
        fragments.add(new LikeFragment());
        fragments.add(new SetFragment());
        fragments.add(new AboutFragment());
    }
    @Override
    protected void initListener() {
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId != R.id.message && itemId != R.id.option){
                    item.setChecked(true);
                    switch (itemId) {
                        case R.id.daily:
                            switchFragment(TYPE_ZHIHU);
                            toolbar.setTitle("知乎日报");
                            break;
                        case R.id.wechat:
                            switchFragment(TYPE_WECHAT);
                            toolbar.setTitle("微信精选");
                            break;
                        case R.id.gank:
                            switchFragment(TYPE_GANK);
                            toolbar.setTitle("干货集中营");
                            break;
                        case R.id.gold:
                            switchFragment(TYPE_GOLD);
                            toolbar.setTitle("稀土掘金");
                            break;
                        case R.id.vtex:
                            switchFragment(TYPE_V2EX);
                            toolbar.setTitle("V2EX");
                            mSearchView.setVisibility(View.GONE);
                            break;
                        case R.id.like:
                            switchFragment(TYPE_COLLECT);
                            toolbar.setTitle("收藏");
                            break;
                        case R.id.setting:
                            switchFragment(TYPE_SETTINGS);
                            toolbar.setTitle("设置");
                            break;
                        case R.id.about:
                            switchFragment(TYPE_ABOUT);
                            toolbar.setTitle("关于");
                            break;
                    }
                    dl.closeDrawer(Gravity.LEFT);
                }else {
                    item.setChecked(false);
                }
                return false;
            }
        });
        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                EventBus.getDefault().postSticky(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

            }
        });
    }

    private void switchFragment(int type) {
        //显示一个fragmnet,隐藏一个Fragment
        //显示
        Fragment fragment = fragments.get(type);
        Integer integer = integers.get(type);

        //需要隐藏
        Fragment hideFragment = fragments.get(mLastFragmentPosition);
        FragmentTransaction transaction = manager.beginTransaction();
        if (!fragment.isAdded()){
            transaction.add(R.id.fl,fragment);
        }

        transaction.hide(hideFragment);
        transaction.show(fragment);
        transaction.commit();
        toolbar.setTitle(integer);
        mLastFragmentPosition = type;
        if (type == TYPE_WECHAT || type == TYPE_GANK){
            mSearchItem.setVisible(true);
        }else {
            mSearchItem.setVisible(false);
        }
    }
    /*周林  1808B*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu,menu);
        mSearchItem = menu.findItem(R.id.action_search);
        mSearchItem.setVisible(false);
        mSearchView.setMenuItem(mSearchItem);
        return true;
    }
    /**
     * 回退键点击回调
     */
    @Override
    public void onBackPressed() {
        if (mSearchView.isSearchOpen()) {
            mSearchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }
}
