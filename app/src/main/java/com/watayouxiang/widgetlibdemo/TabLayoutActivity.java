package com.watayouxiang.widgetlibdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.watayouxiang.demoshell.DemoActivity;
import com.watayouxiang.demoshell.ListData;
import com.watayouxiang.widgetlibrary.tablayout.TaoTabLayoutAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutActivity extends DemoActivity {
    private TaoTabLayoutAdapter tabLayoutAdapter;

    @Override
    protected int getHolderViewId() {
        return R.layout.activity_tablayout;
    }

    @Override
    protected ListData getListData() {
        return new ListData()
                .addClick("清空数据", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tabLayoutAdapter.setNewData(null);
                    }
                })
                .addClick("设置数据", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tabLayoutAdapter.setNewData(getTestData());
                    }
                })
                .addClick("选中最后一个", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int size = tabLayoutAdapter.getData().size();
                        tabLayoutAdapter.setCurrentItem(size - 1);
                    }
                })
                ;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        tabLayoutAdapter = new TaoTabLayoutAdapter(this, recyclerView);
        tabLayoutAdapter.setNewData(getTestData());
        tabLayoutAdapter.setOnItemClickListener(new TaoTabLayoutAdapter.OnItemClickListener() {
            @Override
            public boolean onItemClick(TaoTabLayoutAdapter adapter, View view, int position) {
                String text = adapter.getData().get(position);
                Toast.makeText(view.getContext(), text, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private List<String> getTestData() {
        List<String> list = new ArrayList<>();
        list.add("推荐");
        list.add("初级课程");
        list.add("中级课程");
        list.add("高级课程");
        list.add("VIP课程");
        return list;
    }
}
