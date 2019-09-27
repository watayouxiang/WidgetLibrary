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
import com.watayouxiang.widgetlibrary.tablayout.TaoViewHolder;
import com.watayouxiang.widgetlibrary.tablayout.custom.CategoryTabAdapter;
import com.watayouxiang.widgetlibrary.tablayout.custom.NavigateTabAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutActivity extends DemoActivity {
    private NavigateTabAdapter mTabAdapter;

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
                        mTabAdapter.setNewData(null);
                    }
                })
                .addClick("设置数据", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mTabAdapter.setNewData(getTestData());
                    }
                })
                .addClick("选中最后一个", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mTabAdapter.setCurrentItem(mTabAdapter.getData().size() - 1);
                    }
                })
                ;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        initTabLayout();
        initTabLayout2();
        initTabLayout3();
    }

    private void initTabLayout3() {
        RecyclerView tabLayout = findViewById(R.id.rv_tabLayout3);
        final CategoryTabAdapter tabAdapter = new CategoryTabAdapter(this, tabLayout);
        tabAdapter.setOnItemClickListener(new TaoOnItemClickListener() {
            @Override
            public boolean onItemClick(TaoViewHolder viewHolder) {
                String text = tabAdapter.getData().get(viewHolder.getLayoutPosition());
                Toast.makeText(viewHolder.itemView.getContext(), text, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        tabAdapter.setNewData(getTestData());
    }

    private void initTabLayout2() {
        RecyclerView tabLayout = findViewById(R.id.rv_tabLayout2);
        ViewPager viewPager = findViewById(R.id.viewPager2);
        viewPager.setAdapter(new VpAdapter(getSupportFragmentManager(), getTestData()));
        NavigateTabAdapter tabAdapter = new NavigateTabAdapter(this, tabLayout);
        tabAdapter.setViewPager(viewPager);
    }

    private void initTabLayout() {
        RecyclerView tabLayout = findViewById(R.id.rv_tabLayout);
        mTabAdapter = new NavigateTabAdapter(this, tabLayout);
        mTabAdapter.setOnItemClickListener(new TaoOnItemClickListener() {
            @Override
            public boolean onItemClick(TaoViewHolder viewHolder) {
                String text = mTabAdapter.getData().get(viewHolder.getLayoutPosition());
                Toast.makeText(viewHolder.itemView.getContext(), text, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        mTabAdapter.setNewData(getTestData());
    }

    private List<String> getTestData() {
        List<String> list = new ArrayList<>();
        list.add("推荐");
        list.add("初级课程");
        list.add("中级课程");
        list.add("高级课程");
        list.add("VIP课程");
        list.add("电视剧");
        list.add("电影");
        list.add("综艺");
        list.add("动漫");
        list.add("杭州");
        list.add("小视频");
        list.add("体育");
        return list;
    }
}
