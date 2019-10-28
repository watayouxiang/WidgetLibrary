package com.watayouxiang.widgetlibdemo;

import com.watayouxiang.demoshell.DemoActivity;
import com.watayouxiang.demoshell.ListData;

public class StaticItemActivity extends DemoActivity {
    @Override
    protected int getHolderViewId() {
        return 0;
    }

    @Override
    protected ListData getListData() {
        return new ListData();
    }
}
