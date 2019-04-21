package com.example.geek.widjet;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class SimpleTouchHelperCallBack extends ItemTouchHelper.Callback {
    private TouchCallBack mCallBack ;
    private boolean mSwipeEnable = true;
    public SimpleTouchHelperCallBack(TouchCallBack callBack){
        this.mCallBack = callBack;
    }
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int drag = ItemTouchHelper.UP|ItemTouchHelper.DOWN;
        int swipe = ItemTouchHelper.LEFT;
        return makeMovementFlags(drag,swipe);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        mCallBack.onItemMove(viewHolder.getAdapterPosition(),viewHolder1.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        mCallBack.onItemDelete(viewHolder.getAdapterPosition());
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }
    public void setSwipeEnable(boolean enable){
        mSwipeEnable = enable;
    }
}
