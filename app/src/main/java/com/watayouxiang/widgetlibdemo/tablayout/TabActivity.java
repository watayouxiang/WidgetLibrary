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
import com.watayouxiang.widgetlibrary.tablayout.custom.FilterTabAdapter;
import com.watayouxiang.widgetlibrary.tablayout.custom.NavigateTabAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabActivity extends DemoActivity {
    private NavigateTabAdapter tabAdapter;
    private NavigateTabAdapter tabAdapter2;
    private CategoryTabAdapter tabAdapter3;
    private FilterTabAdapter tabAdapter4;

    @Override
    protected int getHolderViewId() {
        return R.layout.activity_tab;
    }

    @Override
    protected ListData getListData() {
        return new ListData()
                .addClick("清空数据", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tabAdapter.setNewData(null);
                        tabAdapter2.setNewData(null);
                        tabAdapter3.setNewData(null);
                        tabAdapter4.setNewData(null);
                    }
                })
                .addClick("设置新数据", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tabAdapter.setNewData(getTestData());
                        tabAdapter2.setNewData(getTestData());
                        tabAdapter3.setNewData(getTestData());
                        tabAdapter4.setNewData(getTestData());
                    }
                })
                .addClick("选中最后一个", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tabAdapter.setCurrentItem(tabAdapter.getItemCount() - 1);
                        tabAdapter2.setCurrentItem(tabAdapter2.getItemCount() - 1);
                        tabAdapter3.setCurrentItem(tabAdapter3.getItemCount() - 1);
                        tabAdapter4.setCurrentItem(tabAdapter4.getItemCount() - 1);
                    }
                })
                ;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        ViewPager viewPager = findViewById(R.id.vp_common);
        RecyclerView tabLayout = findViewById(R.id.rv_tabLayout);
        RecyclerView tabLayout2 = findViewById(R.id.rv_tabLayout2);
        RecyclerView tabLayout3 = findViewById(R.id.rv_tabLayout3);
        RecyclerView tabLayout4 = findViewById(R.id.rv_tabLayout4);
        initTabLayout(tabLayout);
        initTabLayout2(tabLayout2, viewPager);
        initTabLayout3(tabLayout3, viewPager);
        initTabLayout4(tabLayout4, viewPager);
    }

    private void initTabLayout4(RecyclerView tabLayout4, ViewPager viewPager) {
        viewPager.setAdapter(new VpAdapter(getSupportFragmentManager(), getTestData()));
        tabAdapter4 = new FilterTabAdapter(tabLayout4);
        tabAdapter4.setViewPager(viewPager);
    }

    private void initTabLayout3(RecyclerView tabLayout3, ViewPager viewPager) {
        viewPager.setAdapter(new VpAdapter(getSupportFragmentManager(), getTestData()));
        tabAdapter3 = new CategoryTabAdapter(tabLayout3);
        tabAdapter3.setViewPager(viewPager);
    }

    private void initTabLayout2(RecyclerView tabLayout2, ViewPager viewPager) {
        viewPager.setAdapter(new VpAdapter(getSupportFragmentManager(), getTestData()));
        tabAdapter2 = new NavigateTabAdapter(tabLayout2);
        tabAdapter2.setViewPager(viewPager);
    }

    private void initTabLayout(RecyclerView tabLayout) {
        tabAdapter = new NavigateTabAdapter(tabLayout);
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
