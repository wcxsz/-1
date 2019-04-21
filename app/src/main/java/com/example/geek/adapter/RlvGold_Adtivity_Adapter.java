package com.example.geek.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.geek.R;
import com.example.geek.bean.GoldShowBean;
import com.example.geek.widjet.TouchCallBack;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvGold_Adtivity_Adapter extends RecyclerView.Adapter<RlvGold_Adtivity_Adapter.MyHolder> implements TouchCallBack {
    private ArrayList<GoldShowBean> mList;
    private Context context;

    public RlvGold_Adtivity_Adapter(ArrayList<GoldShowBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    public void setmList(ArrayList<GoldShowBean> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_item_gold_activity, null);
        MyHolder myHolder = new MyHolder(inflate);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        myHolder.tv_gold_activity.setText(mList.get(i).title);
        myHolder.sc_gold_activity.setChecked(mList.get(i).isChecked);
        myHolder.sc_gold_activity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mList.get(i).isChecked = isChecked;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mList,fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);
    }

    @Override
    public void onItemDelete(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_gold_activity)
        TextView tv_gold_activity;
        @BindView(R.id.sc_gold_activity)
        SwitchCompat sc_gold_activity;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
