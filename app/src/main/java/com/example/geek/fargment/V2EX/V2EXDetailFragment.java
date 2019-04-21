package com.example.geek.fargment.V2EX;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.geek.R;
import com.example.geek.adapter.V2EXDetailAdapter;
import com.example.geek.base.BaseFragment;
import com.example.geek.bean.V2EXDetailBean;
import com.example.geek.presenter.EmptyPresenter;
import com.example.geek.presenter.V2EXDetailPresenter;
import com.example.geek.view.EmptyView;
import com.example.geek.view.V2EXDetailView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class V2EXDetailFragment extends BaseFragment<V2EXDetailView, V2EXDetailPresenter> {
    @BindView(R.id.rlv_v2ex_detail)
    RecyclerView rlv_v2ex_detail;
    private String string;
    private String img = "";
    private String count = "";
    private String title = "";
    private String programmer = "";
    private String superzou = "";
    private String time = "";
    private String livid = "";
    private static final String TAG = "V2EXDetailFragment";
    private ArrayList<V2EXDetailBean> list;


    public V2EXDetailFragment(String string) {
        this.string = string;
    }

    private String mUrl = "https://www.v2ex.com/";

    @Override
    protected V2EXDetailPresenter initPresenter() {
        return new V2EXDetailPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_v2_exdetail;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        new Thread() {

            private String text;
            private String commentCount;
            private String src;
            private String topic;

            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(mUrl).get();
                    //查找id是Tabs的div元素,因为只有一个,直接调用了first()
                    //Element tabs = doc.select("div#Tabs").first();
                    //新闻的子条目数据
                    /*  public String img;
                        public String title;
                        public String count;
                        public String programmer;
                        public String superzou;
                        public String livid;
                        public String time;*/
                    Elements items = doc.select("div.cell.item");
                    for (Element element : items) {
                        //图片
                        Element image = element.select("table tbody tr td > a >img.avatar").first();
                        src = image.attr("src");

                        //Log.d(TAG, "图片: " + src);

                        //评论,有可能没有,需要判空
                        Element comment = element.select("table tbody tr td >a.count_livid").first();
                        if (comment != null) {
                            commentCount = comment.text();
                            String href = comment.attr("href");

                            //Log.d(TAG, "评论数量: " + commentCount + ",href:" + href);
                        }

                        //新闻的主体信息
                        Element titleElement = element.select("table tbody tr td span.item_title > a").first();
                        text = titleElement.text();

                        //Log.d(TAG, "标题: " + text);

                        //评论的信息
                        Elements topicElement = element.select("table tbody tr td span.topic_info");
                        topic = topicElement.text();
                        img += src;
                        Log.d(TAG,img);
                        count += commentCount;
                        Log.d(TAG,count);
                        title += text;
                        Log.d(TAG,title);
                        String[] split = topic.split("•");
                        if (split.length == 4){
                            programmer = split[0];
                            superzou = split[1];
                            time = split[2];
                            livid = split[3];
                        }
                        Log.d(TAG,programmer);
                        Log.d(TAG,superzou);
                        Log.d(TAG,time);
                        Log.d(TAG,livid);
                        //Log.d(TAG, "topic: " + split.length);
                        V2EXDetailBean v2EXDetailBean = new V2EXDetailBean(img, count, title, programmer, superzou, time, livid);
                        list.add(v2EXDetailBean);

                        img = "";
                        count = "";
                        title = "";
                        programmer = "";
                        superzou = "";
                        time = "";
                        livid = "";
                    }

                    getActivity().runOnUiThread(new Runnable() {

                        private V2EXDetailAdapter adapter;

                        @Override
                        public void run() {
                            rlv_v2ex_detail.setLayoutManager(new LinearLayoutManager(getContext()));
                            adapter = new V2EXDetailAdapter(list, getContext());
                            rlv_v2ex_detail.setAdapter(adapter);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
