package com.watayouxiang.widgetlibdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.watayouxiang.demoshell.DemoActivity;
import com.watayouxiang.demoshell.ListData;
import com.watayouxiang.widgetlibrary.NoticeFlipper;

import java.util.Arrays;
import java.util.List;

public class NoticeFlipperActivity extends DemoActivity {
    private NoticeFlipper flipper;
    private List<String> moreData = Arrays.asList(
            "1. 重影问题可参考以下解决方案",
            "2. 心中有阳光，脚底有力量！",
            "3. 有兴趣的话点点关注，明早十点不见不散。"
    );
    private List<String> singleData = Arrays.asList(
            "只发布了一条公告哦"
    );

    @Override
    protected int getHolderViewId() {
        return R.layout.view_noticeflipper;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        flipper = findViewById(R.id.noticeFlipper);
        //设置条目点击监听
        flipper.setOnItemClickListener(new NoticeFlipper.OnItemClickListener() {
            @Override
            public void onNoticeClick(int position, TextView textView) {
                Toast.makeText(textView.getContext(), textView.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        //设置数据并开始轮播
        flipper.startWithList(moreData);
    }

    @Override
    protected ListData getListData() {
        return new ListData()
                .addClick("设置一条公告", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        flipper.startWithList(singleData);
                    }
                })
                .addClick("设置多条公告", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        flipper.startWithList(moreData);
                    }
                })
                .addWeb(this, "精简自MarqueeView", "https://github.com/sunfusheng/MarqueeView")
                .addSection("优势：1、内部实现复用机制，不论数据量多大，均可保持流畅。2、当界面不可见停止轮播，页面可见时恢复轮播。");
    }
}
