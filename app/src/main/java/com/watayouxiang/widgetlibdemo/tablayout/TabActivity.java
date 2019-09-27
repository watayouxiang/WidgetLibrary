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
    private NavigateTabAdapter navigateTabAdapter;
    private CategoryTabAdapter categoryTabAdapter;
    private FilterTabAdapter filterTabAdapter;

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
                        navigateTabAdapter.setNewData(null);
                        categoryTabAdapter.setNewData(null);
                        filterTabAdapter.setNewData(null);
                    }
                })
                .addClick("设置新数据", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        navigateTabAdapter.setNewData(getNavigateData());
                        categoryTabAdapter.setNewData(getCategoryData());
                        filterTabAdapter.setNewData(getFilterData());
                    }
                })
                .addClick("选中最后一个", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        navigateTabAdapter.setCurrentItem(navigateTabAdapter.getItemCount() - 1);
                        categoryTabAdapter.setCurrentItem(categoryTabAdapter.getItemCount() - 1);
                        filterTabAdapter.setCurrentItem(filterTabAdapter.getItemCount() - 1);
                    }
                })
                ;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        ViewPager viewPager = findViewById(R.id.vp_common);
        RecyclerView tabLayout2 = findViewById(R.id.rv_tabLayout2);
        RecyclerView tabLayout3 = findViewById(R.id.rv_tabLayout3);
        RecyclerView tabLayout4 = findViewById(R.id.rv_tabLayout4);
        initNavigateTabAdapter(tabLayout2, viewPager);
        initCategoryTabAdapter(tabLayout3);
        initFilterTabAdapter(tabLayout4);
    }

    private void initFilterTabAdapter(RecyclerView recyclerView) {
        filterTabAdapter = new FilterTabAdapter(recyclerView);
        filterTabAdapter.setNewData(getFilterData());
        filterTabAdapter.setOnItemClickListener(new TaoOnItemClickListener() {
            @Override
            public boolean onItemClick(TaoViewHolder viewHolder) {
                Toast.makeText(
                        viewHolder.itemView.getContext(),
                        filterTabAdapter.getData().get(viewHolder.getLayoutPosition()),
                        Toast.LENGTH_SHORT
                ).show();
                return true;
            }
        });
    }

    private void initCategoryTabAdapter(RecyclerView recyclerView) {
        categoryTabAdapter = new CategoryTabAdapter(recyclerView);
        categoryTabAdapter.setNewData(getCategoryData());
        categoryTabAdapter.setOnItemClickListener(new TaoOnItemClickListener() {
            @Override
            public boolean onItemClick(TaoViewHolder viewHolder) {
                Toast.makeText(
                        viewHolder.itemView.getContext(),
                        categoryTabAdapter.getData().get(viewHolder.getLayoutPosition()),
                        Toast.LENGTH_SHORT
                ).show();
                return true;
            }
        });
    }

    private void initNavigateTabAdapter(RecyclerView recyclerView, ViewPager viewPager) {
        viewPager.setAdapter(new VpAdapter(getSupportFragmentManager(), getNavigateData()));
        navigateTabAdapter = new NavigateTabAdapter(recyclerView);
        navigateTabAdapter.setViewPager(viewPager);
    }

    private List<String> getNavigateData() {
        List<String> list = new ArrayList<>();
        list.add("推荐");
        list.add("初级课程");
        list.add("中级课程");
        list.add("高级课程");
        list.add("VIP课程");
        list.add("白卡课程");
        list.add("紫卡课程");
        list.add("橙卡课程");
        list.add("金色史诗课程");
        list.add("免费课程");
        return list;
    }

    private List<String> getCategoryData() {
        List<String> list = new ArrayList<>();
        list.add("入门");
        list.add("进阶");
        list.add("实战");
        list.add("中阶");
        list.add("高阶");
        return list;
    }

    private List<String> getFilterData() {
        List<String> list = new ArrayList<>();
        list.add("热门");
        list.add("价格");
        list.add("最新");
        return list;
    }
}
