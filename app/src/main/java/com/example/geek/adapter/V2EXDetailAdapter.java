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
import com.example.geek.bean.V2EXDetailBean;

import java.util.ArrayList;

public class V2EXDetailAdapter extends RecyclerView.Adapter<V2EXDetailAdapter.MyHolder> {
    private ArrayList<V2EXDetailBean> list;
    private Context context;

    public V2EXDetailAdapter(ArrayList<V2EXDetailBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(ArrayList<V2EXDetailBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_v2ex_detail, null);
        MyHolder myHolder = new MyHolder(inflate);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        Glide.with(context).load("https"+list.get(i).img).into(myHolder.img);
        myHolder.count.setText(list.get(i).title);
        myHolder.programmer.setText(list.get(i).programmer);
        myHolder.superzou.setText(list.get(i).superzou);
        myHolder.title.setText(list.get(i).count);
        myHolder.time.setText(list.get(i).livid+"  •  "+list.get(i).time);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView count;
        private final TextView programmer;
        private final TextView superzou;
        private final TextView time;
        private final TextView title;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_v2ex_detail);
            count = itemView.findViewById(R.id.count_v2ex_detail);
            programmer = itemView.findViewById(R.id.programmer_v2ex_detail);
            superzou = itemView.findViewById(R.id.superzou_v2ex_detail);
            time = itemView.findViewById(R.id.time_v2ex_detail);
            title = itemView.findViewById(R.id.title_v2ex_detail);

        }
    }
}
