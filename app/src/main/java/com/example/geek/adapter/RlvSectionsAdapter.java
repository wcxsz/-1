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
import com.example.geek.bean.DailySectionsBean;

import java.util.ArrayList;

public class RlvSectionsAdapter extends RecyclerView.Adapter<RlvSectionsAdapter.MyHolder> {
    private ArrayList<DailySectionsBean.DataBean> list;
    private Context context;

    public RlvSectionsAdapter(ArrayList<DailySectionsBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(ArrayList<DailySectionsBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_daily_section, null);
        MyHolder myHolder = new MyHolder(inflate);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.sections_name.setText(list.get(i).getName());
        myHolder.sections_title.setText(list.get(i).getDescription());
        Glide.with(context).load(list.get(i).getThumbnail()).into(myHolder.sections_img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final ImageView sections_img;
        private final TextView sections_name;
        private final TextView sections_title;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            sections_img = itemView.findViewById(R.id.sections_img);
            sections_name = itemView.findViewById(R.id.sections_name);
            sections_title = itemView.findViewById(R.id.sections_title);
        }
    }
}
