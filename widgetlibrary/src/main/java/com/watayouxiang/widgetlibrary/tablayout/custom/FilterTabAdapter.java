package com.watayouxiang.widgetlibrary.tablayout.custom;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.watayouxiang.widgetlibrary.R;
import com.watayouxiang.widgetlibrary.tablayout.TaoTabAdapter;
import com.watayouxiang.widgetlibrary.tablayout.TaoViewHolder;

public class FilterTabAdapter extends TaoTabAdapter {
    public FilterTabAdapter(@NonNull RecyclerView recyclerView) {
        super(recyclerView);
    }

    @Override
    protected int getItemViewLayoutId() {
        return R.layout.tab_filter;
    }

    @Override
    protected void convert(@NonNull TaoViewHolder holder, String text, boolean select) {
        TextView tv_txt = holder.itemView.findViewById(R.id.tv_txt);
        ImageView iv_arrow = holder.itemView.findViewById(R.id.iv_arrow);
        tv_txt.setText(String.valueOf(text));
        tv_txt.setSelected(select);
        iv_arrow.setVisibility(select ? View.VISIBLE : View.GONE);
    }

    @Override
    protected boolean getReverseLayout() {
        return true;
    }
}
