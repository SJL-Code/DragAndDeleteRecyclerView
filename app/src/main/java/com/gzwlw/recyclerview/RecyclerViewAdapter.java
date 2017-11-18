package com.gzwlw.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Message> list;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public RecyclerViewAdapter(Context context, ArrayList<Message> list, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.list = list;
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 添加一个额外的方法通知Adapter数据发生改变
     *
     * @param position 位置
     */
    public void onItemRemove(int position) {
        // 移除数据
        list.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;

        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, null);
        viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.userImageView.setImageDrawable(list.get(position).getDrawable());
        myViewHolder.timeTextView.setText(list.get(position).getTime());
        myViewHolder.userNameTextView.setText(list.get(position).getUserName());
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView userImageView;
        private TextView timeTextView;
        private TextView userNameTextView;

        private MyViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 4.在点击事件中调用接口里的方法
                    if (null != onItemClickListener) {
                        // holder.getLayoutPosition()方法表示获得当前所点击item的真正位置
                        onItemClickListener.onItemClick(getLayoutPosition());
                    }

                }
            });

            userImageView = itemView.findViewById(R.id.userImage);
            timeTextView = itemView.findViewById(R.id.time);
            userNameTextView = itemView.findViewById(R.id.userName);
        }
    }
}
