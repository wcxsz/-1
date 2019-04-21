package com.example.geek.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.geek.R;
import com.example.geek.bean.GoldTabItemBean;

import java.util.ArrayList;

public class RlvGoldDetailAdapter extends RecyclerView.Adapter<RlvGoldDetailAdapter.MyHolder> {
    private ArrayList<GoldTabItemBean.DataBean.DatasBean> list;
    private Context context;

    public RlvGoldDetailAdapter(ArrayList<GoldTabItemBean.DataBean.DatasBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(ArrayList<GoldTabItemBean.DataBean.DatasBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_gold_detail, null);
        MyHolder myHolder = new MyHolder(inflate);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        myHolder.tv_gold_detail.setText(list.get(i).getDesc());
        Glide.with(context).load(list.get(i).getEnvelopePic()).into(myHolder.iv_gold_detail);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final ImageView iv_gold_detail;
        private final TextView tv_gold_detail;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            iv_gold_detail = itemView.findViewById(R.id.iv_gold_detail);
            tv_gold_detail = itemView.findViewById(R.id.tv_gold_detail);
        }
    }
}
