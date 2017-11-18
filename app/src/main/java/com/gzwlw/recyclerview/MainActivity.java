package com.gzwlw.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * @author SJL
 */
public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnItemClickListener {

    private final String TAG = "MainActivity";

    private ArrayList<Message> list;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initEvent();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    private void initData() {
        list = new ArrayList<>();
        Message message1 = new Message("刘一", "1:00", getResources().getDrawable(R.drawable.a_1));
        Message message2 = new Message("陈二", "2:00", getResources().getDrawable(R.drawable.a_2));
        Message message3 = new Message("张三", "3:00", getResources().getDrawable(R.drawable.a_3));
        Message message4 = new Message("李四", "4:00", getResources().getDrawable(R.drawable.a_4));
        Message message5 = new Message("王五", "5:00", getResources().getDrawable(R.drawable.a_5));
        Message message6 = new Message("赵六", "6:00", getResources().getDrawable(R.drawable.a_6));
        Message message7 = new Message("孙七", "7:00", getResources().getDrawable(R.drawable.a_7));
        Message message8 = new Message("周八", "8:00", getResources().getDrawable(R.drawable.a_8));
        Message message9 = new Message("吴九", "9:00", getResources().getDrawable(R.drawable.a_9));
        Message message10 = new Message("郑十", "10:00", getResources().getDrawable(R.drawable.a_10));
        Message message11 = new Message("米兰", "11:00", getResources().getDrawable(R.drawable.a_11));

        list.add(message1);
        list.add(message2);
        list.add(message3);
        list.add(message4);
        list.add(message5);
        list.add(message6);
        list.add(message7);
        list.add(message8);
        list.add(message9);
        list.add(message10);
        list.add(message11);
    }

    private void initEvent() {
        // LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter(this, list, this);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper helper = new ItemTouchHelper(new RecyclerViewItemTouchHelper(list, adapter));
        helper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onItemClick(int position) {
        Log.e(TAG, "位置: " + position);
        Log.e(TAG, "数据: " + list.toString());
    }
}
