package com.watayouxiang.widgetlibrary.tablayout;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.watayouxiang.widgetlibrary.R;

import java.util.ArrayList;
import java.util.List;

public class TaoTabLayoutAdapter extends RecyclerView.Adapter<TaoTabLayoutAdapter.ViewHolder> {
    private final List<String> mData = new ArrayList<>();
    private final LayoutManager mLayoutManager;
    private final RecyclerView mRecyclerView;
    private OnItemClickListener mOnItemClickListener;
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_tablayout_item, parent, false));
        bindViewClickListener(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String text = mData.get(position);
        boolean select = position == mSelectIndex;
        holder.tv_txt.setText(text != null ? text : "");
        holder.tv_txt.setTextColor(select ? Color.parseColor("#000000") : Color.parseColor("#666666"));
        holder.tv_txt.setTextSize(TypedValue.COMPLEX_UNIT_SP, select ? 16 : 15);
        holder.tv_txt.setTypeface(Typeface.defaultFromStyle(select ? Typeface.BOLD : Typeface.NORMAL));
        holder.v_line.setVisibility(select ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    // ============================================================================
    // private method
    // ============================================================================

    private void bindViewClickListener(final ViewHolder viewHolder) {
        if (viewHolder == null) return;
        if (mOnItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener.onItemClick(TaoTabLayoutAdapter.this, v, viewHolder.getLayoutPosition())) {
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
    public void setOnItemClickListener(@Nullable OnItemClickListener listener) {
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
        TaoTabLayoutAdapter.this.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public boolean onItemClick(TaoTabLayoutAdapter adapter, View view, int position) {
                viewPager.setCurrentItem(position);
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

    // ============================================================================
    // class & interface
    // ============================================================================

    public interface OnItemClickListener {
        /**
         * itemView 点击事件
         *
         * @param adapter  适配器
         * @param view     itemView
         * @param position 位置
         * @return 是否选中
         */
        boolean onItemClick(TaoTabLayoutAdapter adapter, View view, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tv_txt;
        final View v_line;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_txt = itemView.findViewById(R.id.tv_txt);
            v_line = itemView.findViewById(R.id.v_line);
        }
    }
}
