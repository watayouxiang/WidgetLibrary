package com.watayouxiang.widgetlibrary.tablayout.custom;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.watayouxiang.widgetlibrary.R;
import com.watayouxiang.widgetlibrary.tablayout.TaoTabAdapter;
import com.watayouxiang.widgetlibrary.tablayout.TaoViewHolder;

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
    protected void convert(@NonNull TaoViewHolder holder, int position) {
        String name = getData().get(position);
        boolean select = position == getSelectPosition();

        TextView tv_txt = holder.itemView.findViewById(R.id.tv_txt);

        tv_txt.setText(String.valueOf(name));
        tv_txt.setSelected(select);
    }

    @Override
    protected int getDefaultSelectPosition(@NonNull List<String> data) {
        return 0;
    }
}
