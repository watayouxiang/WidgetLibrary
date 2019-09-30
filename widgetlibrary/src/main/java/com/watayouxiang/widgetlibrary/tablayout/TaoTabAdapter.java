package com.watayouxiang.widgetlibrary.tablayout;

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

public abstract class TaoTabAdapter extends RecyclerView.Adapter<TaoViewHolder> {
    private final List<String> mData = new ArrayList<>();
    private final TaoLayoutManager mLayoutManager;
    private final RecyclerView mRecyclerView;
    private TaoOnItemClickListener mOnItemClickListener;
    private int mSelectPosition;

    public TaoTabAdapter(@NonNull RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        recyclerView.setLayoutManager(mLayoutManager = getLayoutManager(recyclerView));
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
        convert(holder, position);
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
     * @param holder   TaoViewHolder
     * @param position 位置
     */
    protected abstract void convert(@NonNull TaoViewHolder holder, int position);

    /**
     * 获取默认选中的位置
     *
     * @param data 数据
     * @return 默认选中的位置
     */
    protected int getDefaultSelectPosition(@NonNull List<String> data) {
        return 0;
    }

    /**
     * 获取布局管理器
     *
     * @param recyclerView RecyclerView
     * @return 布局管理器 {@link TaoLayoutManager}
     */
    protected TaoLayoutManager getLayoutManager(@NonNull RecyclerView recyclerView) {
        return new TaoLayoutManager(recyclerView.getContext(), 1, RecyclerView.HORIZONTAL, false);
    }

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
        if (mSelectPosition >= 0 && mSelectPosition < getItemCount()) {
            mLayoutManager.smoothScrollToPosition(mRecyclerView, new RecyclerView.State(), mSelectPosition);
        }
    }

    // ============================================================================
    // public method
    // ============================================================================

    /**
     * 获取选中的位置
     *
     * @return 位置
     */
    public int getSelectPosition() {
        return mSelectPosition;
    }

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
        mSelectPosition = getDefaultSelectPosition(mData);
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
        if (position >= 0 && position < getItemCount()) {
            mSelectPosition = position;
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
                TaoTabAdapter.this.setCurrentItem(position);
            }
        });
        TaoTabAdapter.this.setOnItemClickListener(new TaoOnItemClickListener() {
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
        TaoTabAdapter.this.setNewData(titleArr);
    }
}
