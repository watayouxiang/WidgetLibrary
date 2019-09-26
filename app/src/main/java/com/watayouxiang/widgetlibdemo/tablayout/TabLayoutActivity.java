package com.watayouxiang.widgetlibdemo.tablayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.watayouxiang.demoshell.DemoActivity;
import com.watayouxiang.demoshell.ListData;
import com.watayouxiang.widgetlibdemo.R;
import com.watayouxiang.widgetlibrary.tablayout.TaoOnItemClickListener;
import com.watayouxiang.widgetlibrary.tablayout.TaoTabLayoutAdapter;
import com.watayouxiang.widgetlibrary.tablayout.TaoViewHolder;

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
                        tabLayoutAdapter.setCurrentItem(tabLayoutAdapter.getData().size() - 1);
                    }
                })
                ;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        initTabLayout();
        initTabLayout2();
    }

    private void initTabLayout2() {
        RecyclerView tabLayout = findViewById(R.id.rv_tabLayout2);
        ViewPager viewPager = findViewById(R.id.viewPager2);
        viewPager.setAdapter(new TestVpAdapter(getSupportFragmentManager()));
        TaoTabLayoutAdapter tabLayoutAdapter2 = new TaoTabLayoutAdapter(this, tabLayout);
        tabLayoutAdapter2.setViewPager(viewPager);
    }

    private void initTabLayout() {
        RecyclerView tabLayout = findViewById(R.id.rv_tabLayout);
        tabLayoutAdapter = new TaoTabLayoutAdapter(this, tabLayout);
        tabLayoutAdapter.setNewData(getTestData());
        tabLayoutAdapter.setOnItemClickListener(new TaoOnItemClickListener() {
            @Override
            public boolean onItemClick(TaoViewHolder viewHolder) {
                String text = tabLayoutAdapter.getData().get(viewHolder.getLayoutPosition());
                Toast.makeText(viewHolder.itemView.getContext(), text, Toast.LENGTH_SHORT).show();
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
