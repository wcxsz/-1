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
import com.example.geek.bean.WeChatBean;
import java.util.ArrayList;

public class RlvWeChatAdapter extends RecyclerView.Adapter<RlvWeChatAdapter.ViewHolder> {
    private ArrayList<WeChatBean.NewslistBean> list;
    private Context context;

    public RlvWeChatAdapter(ArrayList<WeChatBean.NewslistBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setList(ArrayList<WeChatBean.NewslistBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_wechat_item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.name.setText(list.get(i).getDescription());
        viewHolder.title.setText(list.get(i).getTitle());
        Glide.with(context).load(list.get(i).getPicUrl()).into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView img;
        private TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.wechat_name);
            title = itemView.findViewById(R.id.wechat_title);
            img = itemView.findViewById(R.id.wechat_img);
        }
    }
}
