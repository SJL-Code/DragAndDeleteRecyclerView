package com.gzwlw.recyclerview;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.Collections;

/**
 * ItemTouchHelper处理好了关于在RecyclerView上添加拖动排序与滑动删除的所有事情
 */
public class RecyclerViewItemTouchHelper extends ItemTouchHelper.Callback {

    private ArrayList<Message> list;
    private RecyclerViewAdapter adapter;

    public RecyclerViewItemTouchHelper(ArrayList<Message> list, RecyclerViewAdapter adapter) {
        this.list = list;
        this.adapter = adapter;
    }

    /**
     * 获取移动的Flags,来指定可以支持的拖放和滑动的方向
     *
     * @param recyclerView 要移动or删除的RecyclerView的对象
     * @param viewHolder   RecyclerViewAdapter.Adapter里面的ViewHolder对象
     * @return 移动的Flags
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlag; // 拖动
        int swipeFlag; // 侧滑

        // 获取当前RecyclerView的布局管理器
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        // 如果为网格布局
        if (layoutManager instanceof GridLayoutManager) {
            // 允许上下左右的拖动
            dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.UP | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN;
            // 不能滑动
            swipeFlag = 0;
        } else {
            // 如果是纵向Linear布局
            if (((LinearLayoutManager) layoutManager).getOrientation() == LinearLayoutManager.VERTICAL) {
                // 允许上下的拖动
                dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                // 只允许从左到右的侧滑
                swipeFlag = /*ItemTouchHelper.LEFT |*/ ItemTouchHelper.RIGHT;
            } else {
                dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                swipeFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            }
        }

        return makeMovementFlags(dragFlag, swipeFlag);
    }

    /**
     * 当用户拖动一个item从旧的位置移动到新的位置时会调用此方法
     *
     * @param recyclerView 要移动or删除的RecyclerView的对象
     * @param viewHolder   RecyclerViewAdapter.Adapter里面的ViewHolder对象
     * @param target
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        // 滑动事件
        Collections.swap(list, viewHolder.getAdapterPosition(), target.getAdapterPosition());
        adapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    /**
     * 当用户左右滑动item达到删除条件时会调用此方法,一般达到item的一般宽度时才会删除,否则弹回原位置
     *
     * @param viewHolder RecyclerViewAdapter.Adapter里面的ViewHolder对象
     * @param direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        // 侧滑事件
        int position = viewHolder.getAdapterPosition();
        if (direction == ItemTouchHelper.END) {
            list.remove(position);
            adapter.notifyItemRemoved(position);
        }
        adapter.onItemRemove(position);
    }

    /**
     * 当某个item由静止状态变为滑动或拖动状态时调用此方法
     *
     * @param viewHolder  RecyclerViewAdapter.Adapter里面的ViewHolder对象
     * @param actionState
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);

        // 拖动状态
        if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
            // 更改itemView的颜色
            viewHolder.itemView.setBackgroundColor(Color.parseColor("#FFF4F4F4"));
        }
    }

    /**
     * 当用户操作完某个item动画结束时调用此方法
     *
     * @param recyclerView 要移动or删除的RecyclerView的对象
     * @param viewHolder   RecyclerViewAdapter.Adapter里面的ViewHolder对象
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);

        viewHolder.itemView.setBackgroundColor(0);
    }

    /**
     * 是否支持侧滑删除,默认返回true
     *
     * @return true支持 false不支持
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return super.isItemViewSwipeEnabled();
    }

    /**
     * 是否支持长按拖动,默认返回true
     *
     * @return true支持 false不支持
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }
}
