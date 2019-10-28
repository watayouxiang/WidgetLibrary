package com.watayouxiang.widgetlibdemo;

import com.watayouxiang.demoshell.DemoActivity;
import com.watayouxiang.demoshell.ListData;

public class StaticItemActivity extends DemoActivity {
    @Override
    protected int getHolderViewId() {
        return R.layout.view_static_item_demo;
    }

    @Override
    protected ListData getListData() {
        return new ListData();
    }
}
