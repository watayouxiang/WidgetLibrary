package com.watayouxiang.widgetlibrary.tablayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public abstract class TaoTabLayoutAdapter extends RecyclerView.Adapter<TaoViewHolder> {
    private final List<String> mData = new ArrayList<>();
    private final LayoutManager mLayoutManager;
    private final RecyclerView mRecyclerView;
    private TaoOnItemClickListener mOnItemClickListener;
    private final static int mDefaultSelectIndex = 0;
    private int mSelectIndex = mDefaultSelectIndex;

    public TaoTabLayoutAdapter(Context context, RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mLayoutManager = new LayoutManager(context, 1, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(this);
    }

    @NonNull
    @Override
    public TaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TaoViewHolder viewHolder = new TaoViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(getItemViewLayoutId(), parent, false));
        bindViewClickListener(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaoViewHolder holder, int position) {
        convert(holder, getData().get(position), position == mSelectIndex);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    // ============================================================================
    // abstract method
    // ============================================================================

    /**
     * 获取ItemView的布局id
     *
     * @return 布局id
     */
    protected abstract int getItemViewLayoutId();

    /**
     * 初始化ItemView布局
     *
     * @param holder TaoViewHolder
     * @param text   文案
     * @param select 是否被选中
     */
    protected abstract void convert(@NonNull TaoViewHolder holder, String text, boolean select);

    // ============================================================================
    // private method
    // ============================================================================

    private void bindViewClickListener(final TaoViewHolder viewHolder) {
        if (viewHolder == null) return;
        if (mOnItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener.onItemClick(viewHolder)) {
                        setCurrentItem(viewHolder.getLayoutPosition());
                    }
                }
            });
        }
    }

    private void smoothSelectItemToCenter() {
        mLayoutManager.smoothScrollToPosition(mRecyclerView, new RecyclerView.State(), mSelectIndex);
    }

    // ============================================================================
    // public method
    // ============================================================================

    @NonNull
    public List<String> getData() {
        return mData;
    }

    /**
     * 设置新数据
     *
     * @param data 数据
     */
    public void setNewData(@Nullable List<String> data) {
        if (data == null) data = new ArrayList<>();
        mData.clear();
        mData.addAll(data);
        mSelectIndex = mDefaultSelectIndex;
        notifyDataSetChanged();
        smoothSelectItemToCenter();
    }

    /**
     * 设置itemView点击监听
     *
     * @param listener 点击监听
     */
    public void setOnItemClickListener(@Nullable TaoOnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    /**
     * 选中某个itemView
     *
     * @param position 位置
     */
    public void setCurrentItem(int position) {
        if (position >= 0 && position < mData.size()) {
            mSelectIndex = position;
            notifyDataSetChanged();
            smoothSelectItemToCenter();
        }
    }

    /**
     * 搭配ViewPager
     *
     * @param viewPager ViewPager
     */
    public void setViewPager(final ViewPager viewPager) {
        //容错处理
        if (viewPager == null) return;
        PagerAdapter viewPagerAdapter = viewPager.getAdapter();
        if (viewPagerAdapter == null) return;

        //设置联动
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                TaoTabLayoutAdapter.this.setCurrentItem(position);
            }
        });
        TaoTabLayoutAdapter.this.setOnItemClickListener(new TaoOnItemClickListener() {
            @Override
            public boolean onItemClick(TaoViewHolder viewHolder) {
                viewPager.setCurrentItem(viewHolder.getLayoutPosition());
                return true;
            }
        });

        //设置数据
        ArrayList<String> titleArr = new ArrayList<>();
        for (int i = 0; i < viewPagerAdapter.getCount(); i++) {
            String pageTitle = (String) viewPagerAdapter.getPageTitle(i);
            if (pageTitle != null) {
                titleArr.add(pageTitle);
            }
        }
        TaoTabLayoutAdapter.this.setNewData(titleArr);
    }
}
