package com.example.geek.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.geek.R;
import com.example.geek.bean.DailyHotBean;
import com.example.geek.bean.DailySectionsBean;

import java.util.ArrayList;

public class RlvHotAdapter extends RecyclerView.Adapter<RlvHotAdapter.MyHolder> {
    private ArrayList<DailyHotBean.RecentBean> list;
    private Context context;

    public RlvHotAdapter(ArrayList<DailyHotBean.RecentBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(ArrayList<DailyHotBean.RecentBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_daily_hot, null);
        MyHolder myHolder = new MyHolder(inflate);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.sections_name.setText(list.get(i).getTitle());
        Glide.with(context).load(list.get(i).getThumbnail()).into(myHolder.sections_img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final ImageView sections_img;
        private final TextView sections_name;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            sections_img = itemView.findViewById(R.id.hot_img);
            sections_name = itemView.findViewById(R.id.hot_title);
        }
    }
}
