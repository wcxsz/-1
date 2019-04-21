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
import com.example.geek.bean.DailyNewCalendarBean;

import java.util.ArrayList;

public class RlvDailyClendarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<DailyNewCalendarBean.StoriesBean> list;
    private Context context;
    private String date;
    private static final int TYPE_TIME = 1;
    private static final int TYPE_NEWS = 2;
    public RlvDailyClendarAdapter(ArrayList<DailyNewCalendarBean.StoriesBean> list, Context context, String date) {
        this.list = list;
        this.context = context;
        this.date = date;
    }

    public void setList(ArrayList<DailyNewCalendarBean.StoriesBean> list) {
        this.list = list;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder holder  = null;
        if (viewType == TYPE_TIME){
            View inflate = LayoutInflater.from(context).inflate(R.layout.layout_daily_new_text, null);
            holder = new TextMyHolder(inflate);
        }else if (viewType == TYPE_NEWS){
            View inflate = LayoutInflater.from(context).inflate(R.layout.layout_daily_new_item, null);
            holder = new ItemMyHolder(inflate);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int viewType = getItemViewType(i);
        if (viewType == TYPE_TIME){
            TextMyHolder text = (TextMyHolder) viewHolder;
            text.textView.setText(date);
        }else if (viewType == TYPE_NEWS){
            ItemMyHolder items = (ItemMyHolder) viewHolder;
            int newPosition = i-1;
            items.textView.setText(list.get(newPosition).getTitle());
            Glide.with(context).load(list.get(newPosition).getImages().get(0)).into(items.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return TYPE_TIME;
        }else {
            return TYPE_NEWS;
        }
    }
    class TextMyHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public TextMyHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.new_text);
        }
    }
    class ItemMyHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ItemMyHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.new_item_img);
            textView = itemView.findViewById(R.id.new_item_title);

        }
    }
}
