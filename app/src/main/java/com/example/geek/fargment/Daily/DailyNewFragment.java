package com.example.geek.fargment.Daily;


import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.geek.R;
import com.example.geek.activity.CalendarActivity;
import com.example.geek.adapter.RlvDailyClendarAdapter;
import com.example.geek.adapter.RlvDailyNewsAdapter;
import com.example.geek.base.BaseFragment;
import com.example.geek.bean.DailyNewBean;
import com.example.geek.bean.DailyNewCalendarBean;
import com.example.geek.presenter.DailyNewPresenter;
import com.example.geek.view.DailyNewView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DailyNewFragment extends BaseFragment<DailyNewView,DailyNewPresenter> implements DailyNewView {

    @BindView(R.id.rlv_daily)
    RecyclerView rlv;
    @BindView(R.id.fab_daily)
    FloatingActionButton mfloating;
    private ArrayList<DailyNewBean.StoriesBean> storiesBeans;
    private ArrayList<DailyNewBean.TopStoriesBean> topStoriesBeans;
    private RlvDailyNewsAdapter adapter;
    private String format;
    private String string = "";
    private ArrayList<DailyNewCalendarBean.StoriesBean> listCalendar;
    private RlvDailyClendarAdapter dailyClendarAdapter;

    @Override
    protected DailyNewPresenter initPresenter() {
        return new DailyNewPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily_new;
    }
    @Override
    protected void initView() {
        storiesBeans = new ArrayList<>();
        topStoriesBeans = new ArrayList<>();
        listCalendar = new ArrayList<>();
        rlv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RlvDailyNewsAdapter(getContext(), this.storiesBeans, topStoriesBeans);
        rlv.setAdapter(adapter);
        mfloating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalendarActivity.class);
                startActivityForResult(intent,100);

            }
        });

    }

    private void initDataCalendar() {
        listCalendar.clear();
        mpresenter.getData1("news/before/"+string);
        dailyClendarAdapter = new RlvDailyClendarAdapter(listCalendar, getContext(), string);
        rlv.setAdapter(dailyClendarAdapter);
    }

    @Override
    protected void initData() {
        mpresenter.getData();
    }


    @Override
    public void setData(DailyNewBean bean) {
        List<DailyNewBean.StoriesBean> stories = bean.getStories();
        List<DailyNewBean.TopStoriesBean> top_stories = bean.getTop_stories();
        storiesBeans.addAll(stories);
        topStoriesBeans.addAll(top_stories);
        adapter.setBann(topStoriesBeans);
        adapter.setItem(storiesBeans);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void setDataCalendar(DailyNewCalendarBean bean) {
        String date = bean.getDate();
        List<DailyNewCalendarBean.StoriesBean> stories = bean.getStories();
        listCalendar.addAll(stories);
        dailyClendarAdapter.setDate(date);
        dailyClendarAdapter.setList(listCalendar);
        dailyClendarAdapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100&&resultCode == 300){
            format = data.getStringExtra("format");
            string += format;
            Toast.makeText(getContext(), format, Toast.LENGTH_SHORT).show();
            initDataCalendar();
            string = "";
        }
    }
}
