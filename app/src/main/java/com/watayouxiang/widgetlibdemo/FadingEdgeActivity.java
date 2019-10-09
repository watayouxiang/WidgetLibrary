package com.watayouxiang.widgetlibdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.watayouxiang.demoshell.DemoActivity;
import com.watayouxiang.demoshell.ListData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FadingEdgeActivity extends DemoActivity {
    private RecyclerView rv;

    @Override
    protected CharSequence getPageTitle() {
        return "FadingEdge使用示例";
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        rv = findViewById(R.id.rv);
    }

    @Override
    protected int getHolderViewId() {
        return R.layout.view_fading_edge;
    }

    @Override
    protected ListData getListData() {
        return new ListData()
                .addSection("FadingEdge的简单使用：")
                .addSection("android:fadingEdge=\"vertical\"")
                .addSection("android:fadingEdgeLength=\"40dp\"")
                .addSection("android:requiresFadingEdge=\"vertical\"")
                .addWeb(this,
                        "参考: Android开发之神奇的Fading Edge，让你的View更有层次感！",
                        "https://blog.csdn.net/u012702547/article/details/52913538")
                ;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        ListAdapter listAdapter = new ListAdapter(rv);
        listAdapter.setData(Arrays.asList(
                "庆", "祝", "中", "华", "人",
                "民", "共", "和", "国", "成",
                "立", "70", "周", "年"
        ));
    }

    class ListAdapter extends RecyclerView.Adapter<MyHolder> {
        private List<String> mData = new ArrayList<>();

        ListAdapter(RecyclerView rv) {
            rv.setLayoutManager(new LinearLayoutManager(rv.getContext(), RecyclerView.VERTICAL, false));
            rv.setAdapter(this);
        }

        void setData(List<String> data) {
            mData.clear();
            if (data != null)
                mData.addAll(data);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MyHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_fading_edge, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.tv_msg.setText(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private final TextView tv_msg;

        MyHolder(@NonNull View itemView) {
            super(itemView);
            tv_msg = itemView.findViewById(R.id.tv_msg);
        }
    }
}
