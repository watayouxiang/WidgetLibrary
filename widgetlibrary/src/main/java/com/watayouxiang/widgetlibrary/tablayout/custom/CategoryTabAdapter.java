package com.watayouxiang.widgetlibrary.tablayout.custom;

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

public class CategoryTabAdapter extends TaoTabAdapter {
    public CategoryTabAdapter(@NonNull RecyclerView recyclerView) {
        super(recyclerView);
    }

    @Override
    protected int getItemViewLayoutId() {
        return R.layout.tab_category;
    }

    @Override
    protected void convert(@NonNull TaoViewHolder holder, String text, boolean select) {
        TextView tv_txt = holder.itemView.findViewById(R.id.tv_txt);
        tv_txt.setText(String.valueOf(text));
        tv_txt.setSelected(select);
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
