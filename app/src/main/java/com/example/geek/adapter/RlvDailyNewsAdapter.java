package com.example.geek.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.geek.R;
import com.example.geek.bean.DailyNewBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvDailyNewsAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<DailyNewBean.StoriesBean> item;
    private ArrayList<DailyNewBean.TopStoriesBean> bann;
    private static final int TYPE_BANNER = 0;
    private static final int TYPE_TIME = 1;
    private static final int TYPE_NEWS = 2;
    private String mDate = "今日新闻";

    public RlvDailyNewsAdapter(Context context, ArrayList<DailyNewBean.StoriesBean> item, ArrayList<DailyNewBean.TopStoriesBean> bann) {
        this.context = context;
        this.item = item;
        this.bann = bann;
    }

    public void setItem(ArrayList<DailyNewBean.StoriesBean> item) {
        this.item = item;
    }

    public void setBann(ArrayList<DailyNewBean.TopStoriesBean> bann) {
        this.bann = bann;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder holder  = null;
        if (viewType == TYPE_BANNER){
            View inflate = LayoutInflater.from(context).inflate(R.layout.layout_daily_new_bann, null);
            holder = new BannMyHolder(inflate);
        }else if (viewType == TYPE_TIME){
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
        if (viewType == TYPE_BANNER){
            BannMyHolder banner = (BannMyHolder) viewHolder;
            banner.banner.setImages(bann)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path,
                                                 ImageView imageView) {
                            DailyNewBean.TopStoriesBean bean = (DailyNewBean.TopStoriesBean) path;
                            Glide.with(context).load(bean.getImage()).into(imageView);
                        }
                    }).start();
        }else if (viewType == TYPE_TIME){
            TextMyHolder text = (TextMyHolder) viewHolder;
            text.textView.setText(mDate);
        }else if (viewType == TYPE_NEWS){
            ItemMyHolder items = (ItemMyHolder) viewHolder;
            int newPosition = i-1;
            if (bann.size()>0){
                newPosition -= 1;
            }
            items.textView.setText(item.get(newPosition).getTitle());
            Glide.with(context).load(item.get(newPosition).getImages().get(0)).into(items.imageView);
        }
    }

    @Override
    public int getItemCount() {
        if (bann.size()>0){
            return item.size()+1+1;
        }else {
            return item.size()+1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (bann.size()>0){
            if (position == 0){
                return TYPE_BANNER;
            }else if (position == 1){
                return TYPE_TIME;
            }else {
                return TYPE_NEWS;
            }
        }else {
            if (position == 0){
                return TYPE_TIME;
            }else {
                return TYPE_NEWS;
            }
        }
    }

    class BannMyHolder extends RecyclerView.ViewHolder {
            Banner banner;
        public BannMyHolder(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.new_banner);
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
