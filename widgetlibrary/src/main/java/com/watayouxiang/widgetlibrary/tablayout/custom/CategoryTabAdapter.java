package com.watayouxiang.widgetlibrary.tablayout.custom;

import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.watayouxiang.widgetlibrary.R;
import com.watayouxiang.widgetlibrary.tablayout.TaoTabAdapter;
import com.watayouxiang.widgetlibrary.tablayout.TaoViewHolder;

public class CategoryTabAdapter extends TaoTabAdapter {
    public CategoryTabAdapter(Context context, RecyclerView recyclerView) {
        super(context, recyclerView);
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
}
