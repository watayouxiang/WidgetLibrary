package com.watayouxiang.widgetlibrary.tablayout.custom;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.watayouxiang.widgetlibrary.R;
import com.watayouxiang.widgetlibrary.tablayout.TaoLayoutManager;
import com.watayouxiang.widgetlibrary.tablayout.TaoTabAdapter;
import com.watayouxiang.widgetlibrary.tablayout.TaoViewHolder;

import java.util.Collections;
import java.util.List;

public class FilterTabAdapter extends TaoTabAdapter {
    public FilterTabAdapter(@NonNull RecyclerView recyclerView) {
        super(recyclerView);
    }

    @Override
    protected int getItemViewLayoutId() {
        return R.layout.tab_filter;
    }

    @Override
    protected void convert(@NonNull TaoViewHolder holder, int position) {
        String name = getData().get(position);
        boolean select = position == getSelectPosition();

        TextView tv_txt = holder.itemView.findViewById(R.id.tv_txt);
        ImageView iv_arrow = holder.itemView.findViewById(R.id.iv_arrow);

        tv_txt.setText(String.valueOf(name));
        tv_txt.setSelected(select);
        iv_arrow.setVisibility(select ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    protected int getDefaultSelectPosition(@NonNull List<String> data) {
        int size = data.size();
        return size <= 0 ? 0 : size - 1;
    }

    @Override
    protected TaoLayoutManager getLayoutManager(@NonNull RecyclerView recyclerView) {
        return new TaoLayoutManager(recyclerView.getContext(), 1, RecyclerView.HORIZONTAL, true);
    }

    @Override
    public void setNewData(@Nullable List<String> data) {
        if (data != null) {
            Collections.reverse(data);
        }
        super.setNewData(data);
    }
}
