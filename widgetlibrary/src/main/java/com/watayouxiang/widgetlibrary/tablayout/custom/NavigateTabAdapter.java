package com.watayouxiang.widgetlibrary.tablayout.custom;

import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.watayouxiang.widgetlibrary.R;
import com.watayouxiang.widgetlibrary.tablayout.TaoTabAdapter;
import com.watayouxiang.widgetlibrary.tablayout.TaoViewHolder;

public class NavigateTabAdapter extends TaoTabAdapter {
    public NavigateTabAdapter(@NonNull RecyclerView recyclerView) {
        super(recyclerView);
    }

    @Override
    protected int getItemViewLayoutId() {
        return R.layout.tab_navigate;
    }

    @Override
    protected void convert(@NonNull TaoViewHolder holder, int position) {
        TextView tv_txt = holder.itemView.findViewById(R.id.tv_txt);
        View v_line = holder.itemView.findViewById(R.id.v_line);
        tv_txt.setText(String.valueOf(getData().get(position)));
        boolean select = position == getSelectPosition();
        tv_txt.setTextColor(select ? Color.parseColor("#000000") : Color.parseColor("#666666"));
        tv_txt.setTextSize(TypedValue.COMPLEX_UNIT_SP, select ? 16 : 15);
        tv_txt.setTypeface(Typeface.defaultFromStyle(select ? Typeface.BOLD : Typeface.NORMAL));
        v_line.setVisibility(select ? View.VISIBLE : View.INVISIBLE);
    }
}
